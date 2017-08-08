class A {

    func a() {
        for a in b {
            helloFirstLoop.sayHello()
            for a in b {
                helloSecondLoop.sayHello()
            }
        }
    }

    func b() {
        for a in b {
        helloThirdLoop.sayHello()
            for a in b {
                helloFourthLoop.sayHello()
            }
            for a in b {
                helloFifthLoop.sayHello()
            }
        }

        while a > b { }
    }
}
