package main

import (
	"fmt"
	"time"
)

func Ivanov(units int, input chan int) {
	chan1 := make(chan int)
	go Petrov(chan1, input)
	for i := 0; i < units; i++ {
		fmt.Println("Ivanov is taking item")
		time.Sleep(time.Second * 2)
		fmt.Println("Ivanov ended")
		chan1 <- 0
	}
	defer close(chan1)
}

func Petrov(chan1 chan int, input chan int) {
	chan2 := make(chan int)
	fmt.Println("Petrov is waiting")
	go Necheporchuk(chan2, input)
	for {
		val, opened := <-chan1
		if !opened {
			break
		}
		fmt.Println("Petrov is loading")
		time.Sleep(time.Second * 2)
		fmt.Println("Petrov ended")
		chan2 <- val + 1
	}
	defer close(chan2)
}

func Necheporchuk(chan2 chan int, input chan int) {
	fmt.Println("Necheporchuk is waiting")
	count := 0
	for {
		val, opened := <-chan2
		if !opened {
			break
		}
		fmt.Println("Necheporchuk started")
		time.Sleep(time.Second * 2)
		fmt.Println("Necheporchuk ended")
		count = count + val
	}
	input <- count
}

func main() {
	var chan0 = make(chan int)
	go Ivanov(3, chan0)
	res := <-chan0
	fmt.Println(res)
}
