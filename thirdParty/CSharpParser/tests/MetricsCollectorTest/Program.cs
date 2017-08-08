using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MetricsCollectorTest
{
    class TestClass
    {
        public void SomeMethod()
        {
            Console.WriteLine("I'm doing nothing");
            System.Console.WriteLine("Yep, really nothing");
        }
    }
}

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            MetricsCollectorTest.Printer.ActualPrinter printer = new MetricsCollectorTest.Printer.ActualPrinter(Console.Out);
            printer.PrintSelf();
            printer.PrintSumAndMul();
            Console.ReadKey();
        }
    }
}
