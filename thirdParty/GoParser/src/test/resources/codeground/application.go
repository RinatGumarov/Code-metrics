//package application
package main

import (
	"time"
)

type SpaceCube struct {
	x, y, z, w, h, d float32
	complexity       int
}

// Simulate processing the cube's data by sleeping proportionally to cube's
// volume and complexity factor.
func (cube SpaceCube) Process() {
	delay := cube.w * cube.h * cube.d * float32(cube.complexity)
	time.Sleep(time.Duration(delay) * time.Millisecond)
}

// Split the cube into 8 subcubes.
func (cube SpaceCube) Split() (cubes [8]SpaceCube) {
	w2 := cube.w / 2
	h2 := cube.h / 2
	d2 := cube.d / 2
	c8 := cube.complexity / 8 // FIXME: uneven complexity division

	cubes[0].x = cube.x
	cubes[0].y = cube.y
	cubes[0].z = cube.z
	cubes[0].w = w2
	cubes[0].h = h2
	cubes[0].d = d2
	cubes[0].complexity = c8

	cubes[1].x = cube.x + w2
	cubes[1].y = cube.y
	cubes[1].z = cube.z
	cubes[1].w = w2
	cubes[1].h = h2
	cubes[1].d = d2
	cubes[1].complexity = c8

	cubes[2].x = cube.x
	cubes[2].y = cube.y + h2
	cubes[2].z = cube.z
	cubes[2].w = w2
	cubes[2].h = h2
	cubes[2].d = d2
	cubes[2].complexity = c8

	cubes[3].x = cube.x
	cubes[3].y = cube.y
	cubes[3].z = cube.z + d2
	cubes[3].w = w2
	cubes[3].h = h2
	cubes[3].d = d2
	cubes[3].complexity = c8

	cubes[4].x = cube.x + w2
	cubes[4].y = cube.y + h2
	cubes[4].z = cube.z
	cubes[4].w = w2
	cubes[4].h = h2
	cubes[4].d = d2
	cubes[4].complexity = c8

	cubes[5].x = cube.x
	cubes[5].y = cube.y + h2
	cubes[5].z = cube.z + d2
	cubes[5].w = w2
	cubes[5].h = h2
	cubes[5].d = d2
	cubes[5].complexity = c8

	cubes[6].x = cube.x + w2
	cubes[6].y = cube.y
	cubes[6].z = cube.z + d2
	cubes[6].w = w2
	cubes[6].h = h2
	cubes[6].d = d2
	cubes[6].complexity = c8

	cubes[7].x = cube.x + w2
	cubes[7].y = cube.y + h2
	cubes[7].z = cube.z + d2
	cubes[7].w = w2
	cubes[7].h = h2
	cubes[7].d = d2
	cubes[7].complexity = c8

	return
}

func SimpleWorker(workQueue <-chan SpaceCube, resultQueue chan<- SpaceCube) {
	for cube := range workQueue {
		cube.Process()                               // here we sleep simulating busy work
		SendMesssage(func() { resultQueue <- cube }) // resultQueue <- cube
	}
	// FIXME: close(resultQueue)
}