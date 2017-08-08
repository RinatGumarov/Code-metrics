//package network
package main

import (
	"math/rand"
	"sync/atomic"
	"time"
)

const (
	Latency            = 10 * time.Millisecond
	Bandwidth          = 1000                   // 1Gb ethernet
	HeartbeatInterval  = 500 * time.Millisecond // FIXME: what should it be?
	HeartbeatTolerance = 3                      // forget about a node after 3 missed heartbeats
	MTBF               = 24.0                   // Mean Time Between Failures, hours
	TBFDispersion      = 5.0                    // Dispersion for Gaussian distribution of Time Between Failures
)

// FIXME: interface{} instead of SpaceCube to avoid dependence on application.go?
type WorkerFunc func(workQueue <-chan SpaceCube, resultQueue chan<- SpaceCube)

// Statistical counters
var messages_counter uint64

// A function for simulating messaging from one node to another.
// SendMesssage accounts for simulated latency and performs statistical counting.
// Actual information transmission is performed through standard Go channels
// which is incapsulated in send_it function argument.
func SendMesssage(send_it func()) {
	time.Sleep(Latency)
	send_it()
	atomic.AddUint64(&messages_counter, 1)
}

// A function to start a worker node simulation.
// The node will communicate through the workQueue and resultQueue channels.
// It will "fail" according to the given failer function. FIXME: automatically
// kill worker gorutine as it still might communicate through the channels given to it.
// Returns heartbeat channel to monitor node liveness.
func SimpleNode(worker WorkerFunc, failer func(*time.Ticker), workQueue <-chan SpaceCube, resultQueue chan<- SpaceCube) (heartbeat <-chan time.Time) {
	ticker := time.NewTicker(HeartbeatInterval)
	heartbeat = ticker.C
	go worker(workQueue, resultQueue)
	failer(ticker) // failer is supposed to start it's own goroutine.
	return
}

// Simulate node failure by stopping it's heartbeat ticker.
func GaussianFailer(ticker *time.Ticker) {
	ttf := rand.NormFloat64()*TBFDispersion + MTBF
	go func() {
		time.Sleep(time.Duration(ttf) * time.Hour)
		ticker.Stop()
	}()
}