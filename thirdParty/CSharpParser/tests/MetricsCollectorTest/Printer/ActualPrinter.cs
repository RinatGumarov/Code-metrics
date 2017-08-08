using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using abstractPrinter = MetricsCollectorTest.Printer.AbstractPrinter;

namespace MetricsCollectorTest.Printer
{
    class ActualPrinter : abstractPrinter
    {
        private int x, y;
        private PrintHelper printHelper;

        public ActualPrinter(TextWriter outputWriter) : base(outputWriter)
        {
            this.x = 1;
            this.y = 2;
            this.printHelper = new PrintHelper();
        }

        public void PrintSelf()
        {
            PrintInteger(x);
            PrintInteger(y);
        }

        public void PrintSumAndMul()
        {
            int sum = PrintHelper.Sum(x, y);
            int mul = printHelper.Multiply(x, y);
            PrintString(sum.ToString());
            PrintLine(mul.ToString());
        }
    }
}
