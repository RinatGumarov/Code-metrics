package main

import (
    "fmt"
    "time"
    "math/rand"
)

func main() {
    rand.Seed(time.Now().UnixNano())
    fmt.Printf("hello, world\n")
}