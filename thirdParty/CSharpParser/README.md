# CSharpMetricsCollectorJava - currently under development


An object-oriented metrics collector for C# projects written as practical part of my thesis.
It uses a JavaCC parser generation with JTB tool grammar pre-processing.
C# 6 grammar was ported from [here](https://github.com/ljw1004/csharpspec).


A set of metrics it collects:
- Chidamber and Kemerer object-oriented metrics suite
- ~~MOOD metrics~~ - not ready yet


#### Build instruictions:
- Make sure that ``javac``, ``java`` and ``jar`` utilities are in ``PATH``
- Clone project ``git clone https://github.com/asleap/CSharpMetricsCollectorJava`` and go to project root
- If on nix, add execute permissions ``chmod +x build.sh`` and run ``./build.sh`` to generate .jar file
- If on Windows, run ``build.bat``
- To run test, execute ``java -jar CSharpMetricsCollectorJava.jar tests/city_generator out.csv``
- Check out metrics in ``out.csv`` file
