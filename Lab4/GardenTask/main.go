package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"sync"
	"time"
)

func readToFile(m *sync.RWMutex, garden *[4][4]int, wg *sync.WaitGroup) {
	file, err := os.Create("log.txt")
	if err != nil {
		fmt.Println("Unable to create file:", err)
		os.Exit(1)
	}
	defer file.Close()
	for {
		time.Sleep(time.Second * 2)
		for i := 0; i < 4; i++ {
			for j := 0; j < 4; j++ {
				m.RLock()
				file.WriteString(strconv.Itoa((*garden)[i][j]) + " ")
				m.RUnlock()
			}
			file.WriteString("\n")
		}
		file.WriteString("\n")
	}
}
func readToConsole(m *sync.RWMutex, garden *[4][4]int, wg *sync.WaitGroup) {
	for {
		time.Sleep(time.Second * 2)
		for i := 0; i < 4; i++ {
			m.RLock()
			fmt.Println((*garden)[i])
			m.RUnlock()
		}
		fmt.Println()
	}
}
func nature(m *sync.RWMutex, garden *[4][4]int, wg *sync.WaitGroup) {
	for {
		time.Sleep(time.Second * 5)
		m.Lock()
		(*garden)[rand.Intn(4)][rand.Intn(4)] = rand.Intn(1)
		(*garden)[rand.Intn(4)][rand.Intn(4)] = rand.Intn(1)
		(*garden)[rand.Intn(4)][rand.Intn(4)] = rand.Intn(1)
		(*garden)[rand.Intn(4)][rand.Intn(4)] = rand.Intn(1)
		m.Unlock()
	}
}
func gardener(m *sync.RWMutex, garden *[4][4]int, wg *sync.WaitGroup) {
	for {
		time.Sleep(time.Second * 4)
		for i := 0; i < 4; i++ {
			for j := 0; j < 4; j++ {
				m.Lock()
				if (*garden)[i][j] == 0 {
					(*garden)[i][j] = 1
				}
				m.Unlock()
			}
		}
	}
}
func main() {
	var m sync.RWMutex
	var wg = sync.WaitGroup{}
	wg.Add(4)
	var garden = [4][4]int{{1, 1, 1, 1}, {1, 0, 1, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}}
	go gardener(&m, &garden, &wg)
	go nature(&m, &garden, &wg)
	go readToConsole(&m, &garden, &wg)
	go readToFile(&m, &garden, &wg)
	wg.Wait()
}
