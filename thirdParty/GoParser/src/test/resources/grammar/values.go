package main

import "fmt"

func main() {
    fmt.Println("go" + "lang")
    fmt.Println("1+1 =", 1+1234567890)
    fmt.Println("7.0/3.0 =", 7.0/3.0)
    fmt.Println("7.0/3.0 =", 0./0.1)
    fmt.Println(0xABC1234567890)
    fmt.Println(0XABC1234567890)
    fmt.Println(.12345E+5)
    fmt.Println(0i)
    fmt.Println(0.i)
    fmt.Println(1.e+0i)
    fmt.Println(6.67428e-11i)
    fmt.Println('a')
    fmt.Println('ä')
    fmt.Println('\t')
    fmt.Println('\007')
    fmt.Println('\xff')
    fmt.Println("\u65e5\U00008a9e")
    fmt.Println(true && false)
    fmt.Println(true || false)
    fmt.Println(!true)
}
