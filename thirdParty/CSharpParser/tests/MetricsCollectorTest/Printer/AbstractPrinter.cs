using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MetricsCollectorTest.Printer
{
    using writer = System.IO.TextWriter;

    class AbstractPrinter
    {
        protected writer outputWriter;
        
        public AbstractPrinter(writer outputWriter)
        {
            this.outputWriter = outputWriter;
        }

        public virtual void PrintInteger(int i)
        {
            outputWriter.Write(i);
        }

        public virtual void PrintString(string str)
        {
            outputWriter.Write(str);
        }

        public virtual void PrintLine(string line)
        {
            outputWriter.Write(line);
        }
    }
}
