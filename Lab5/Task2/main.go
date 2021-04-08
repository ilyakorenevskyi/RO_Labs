package main

import (
	"fmt"
	"math/rand"
	"sort"
	"sync"
)

type CyclicBarrier struct {
	sync.Mutex
	parties int
	count   int
	signal  chan struct{}
}

func (b *CyclicBarrier) reset() {
	b.Lock()

	b.count = b.parties
	close(b.signal)
	b.signal = make(chan struct{})

	b.Unlock() //...
}
func (b *CyclicBarrier) Awaiting() {
	b.Lock()

	b.count--
	count := b.count
	signal := b.signal

	b.Unlock() //...
	if count > 0 {
		<-signal
	} else {
		b.reset()
	}
}
func NewCyclicBarrier(numberOfParties int) *CyclicBarrier {
	b := &CyclicBarrier{
		parties: numberOfParties,
		count:   numberOfParties,
		signal:  make(chan struct{}),
	}
	return b
}

var lines = [][]string{{"A", "A", "B", "C", "D", " A"},
	{"A", "C", "B", "C", "D", " A"},
	{"A", "D", "B", "C", "B", " A"},
	{"D", "C", "B", "C", "D", " A"},
}

//var s1 = []string{"A","A","B", "C", "D"," A"}
//var s2 = []string{"A","C","B", "C", "D"," A"}
//var s3 = []string{"A","D","B", "C", "B"," A"}
//var s4 = []string{"D","C","B", "C", "D"," A"}

func check() bool {
	var count = []int{0, 0, 0, 0}
	for i := 0; i < 4; i++ {
		for j := 0; j < 6; j++ {
			if lines[i][j] == "A" || lines[i][j] == "B" {
				count[i]++
			}
		}
	}
	sort.Ints(count)
	counter := 0
	for i := 0; i < 3; i++ {
		if count[i] == count[i+1] {
			counter++
		} else {
			counter = 0
		}
		if counter > 2 {
			return true
		}
	}
	return false
}

func stringChanger(line *[]string, group *sync.WaitGroup, cycl *CyclicBarrier) {
	for {
		ind := rand.Intn(len(*line))
		switch symb := string((*line)[ind]); symb {
		case "A":
			(*line)[ind] = "C"
		case "C":
			(*line)[ind] = "A"
		case "B":
			(*line)[ind] = "D"
		case "D":
			(*line)[ind] = "B"
		}
		cycl.Awaiting()
		if check() {
			break
		}
		cycl.Awaiting()
	}
	group.Done()

}

func main() {
	wg := sync.WaitGroup{}
	cycl := NewCyclicBarrier(4)
	wg.Add(4)
	for i := 0; i < 4; i++ {
		go stringChanger(&lines[i], &wg, cycl)
	}

	wg.Wait()

	for i := 0; i < 4; i++ {
		fmt.Println(lines[i])
	}

}
