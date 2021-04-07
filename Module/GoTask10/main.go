package main
import (
	"sync"
	"math/rand"
	"fmt"
)

type Stock struct {
	price int
	lock sync.RWMutex
}

func StockExchange(stock *Stock){
	for{
		stock.lock.RLock()
		fmt.Println(stock.price)
		stock.lock.RUnlock()
	}
}


func Broker(stock *Stock, amount int, cash int){
 	for {
		operation := rand.Int()%2
		if operation == 0{
			toSell := rand.Intn(amount)
			amount -= toSell
			stock.lock.Lock()
			cash += stock.price* toSell
			stock.price -= toSell *2
			stock.lock.Unlock()
		} else {
			stock.lock.Lock()
			canBuy := int(cash/stock.price)
			toBuy := rand.Intn(canBuy)
			amount += toBuy
			cash -= toBuy *stock.price
			stock.price += toBuy
			stock.lock.Unlock()
		}
		fmt.Println("Broker has " ,amount , "number of shares and", cash, "$")

 	}
}


func main(){
	var wg = sync.WaitGroup{}
	wg.Add(1)
	var stock1 = Stock{200, sync.RWMutex{}}
	go StockExchange(&stock1)
	go Broker(&stock1, 10, 34)
	go Broker(&stock1, 15, 250)
	go Broker(&stock1, 4, 900)
	wg.Wait()
}
