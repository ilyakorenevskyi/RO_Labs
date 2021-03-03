package main

import (
	"fmt"
	"strconv"
	"time"
)
func Barber(visitor_sem chan bool, barber_sem chan bool) {
	for {
		fmt.Println("Barber is waiting for clients")
		<-barber_sem
		time.Sleep(time.Second)
		fmt.Println("Barber is working")
		time.Sleep(time.Second )
		fmt.Println("Barber finished work")
		<-visitor_sem
	}
}
func Visitor(visitor_sem chan bool, barber_sem chan bool, num int) {
	time.Sleep(time.Second)
	fmt.Println("Visitor " + strconv.Itoa(num) +" joined the queue")
	visitor_sem<-true
	fmt.Println("Visitor " + strconv.Itoa(num) + " is getting a hair cut")
	barber_sem<-true
}

func main()  {
	var visitor_sem = make(chan bool, 1)
	var barber_sem = make(chan bool, 1)
	fmt.Println("Starting")
	go Barber(visitor_sem, barber_sem)
	go Visitor(visitor_sem, barber_sem, 1)
	go Visitor(visitor_sem, barber_sem, 2)
	go Visitor(visitor_sem, barber_sem, 3)
	go Visitor(visitor_sem, barber_sem, 4)
	go Visitor(visitor_sem, barber_sem, 5)
	time.Sleep(time.Second*20)
}