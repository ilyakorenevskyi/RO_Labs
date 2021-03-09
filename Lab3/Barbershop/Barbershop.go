package main

import (
	"fmt"
	"strconv"
	"time"
)
func Barber(visitor_sem chan bool, barber chan bool, visitor chan bool) {
	for {
		fmt.Println("Barber is waiting for clients")
		<-barber
		fmt.Println("Barber is working")
		time.Sleep(time.Second * 3 )
		fmt.Println("Barber finished work")
		visitor<-true
	}
}
func Visitor(visitor_sem chan bool, barber chan bool, num int, visitor chan bool) {
	time.Sleep(time.Second)
	fmt.Println("Visitor " + strconv.Itoa(num) +" joined the queue")
	visitor_sem<-true
	fmt.Println("Visitor " + strconv.Itoa(num) + " is going to get a hair cut and start sleeping")
	barber<-true
	fmt.Println("Visitor " + strconv.Itoa(num) + " is sleeping")
	<-visitor
	fmt.Println("Visitor " + strconv.Itoa(num) + " has a new haircut")
	<-visitor_sem
}

func main()  {
	var visitor_sem = make(chan bool, 1)
	var barber = make(chan bool, 1)
	var visitor = make(chan bool, 1)
	fmt.Println("Starting")
	go Barber(visitor_sem, barber, visitor)
	go Visitor(visitor_sem, barber, 1 , visitor)
	go Visitor(visitor_sem, barber, 2, visitor)
	go Visitor(visitor_sem, barber, 3, visitor)
	go Visitor(visitor_sem, barber, 4, visitor)
	go Visitor(visitor_sem, barber, 5, visitor)
	time.Sleep(time.Second*30)
}