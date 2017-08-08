//using System.Collections;
//using MyAbstractMath = AbstractMath;

//namespace AbstractMath {
//    public class MathClass<T> {
//        public float this[int index]
//        {
//            get
//            {
//                return temps[index];
//            }
//
//            set
//            {
//                temps[index] = value;
//            }
//        }
//        public string Name
//        {
//            get
//            {
//               return myName;
//            }
//            set
//            {
//               myName = value;
//            }
//        }
//        public static float lerp(ref float a, float b, float t)
//        {
//            return a + (b - a) * t;
//        }
//        public static double t = 0.4;
//        public const int a = 7;
//        public event ChangedEventHandler Changed;
//        public static Complex operator +(Complex c1, Complex c2)
//        {
//            return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
//        }
//
//        public static implicit operator DBBool(bool x)
//        {
//            return x? dbTrue: dbFalse;
//        }
//        public static explicit operator bool(DBBool x)
//        {
//            if (x.value == 0) throw new InvalidOperationException();
//            return x.value > 0;
//        }
//
//    }
//}
//
//namespace Math {
//    public abstract class MathHelper : MyAbstractMath::MathClass, IMathable
//    {
//        public static float lerp(float a, float b, float t)
//        {
//            return a + (b - a) * t;
//        }
//
//        public static float quintic(float t)
//        {
//            return t * t * t * (t * (t * 6 - 15) + 10);
//        }
//
//        public static float cosine(float t)
//        {
//            return (1 - Mathf.Cos(t * Mathf.PI)) / 2;
//        }
//
//        // Test Comment
//        public static float cubic(float t)
//        {
//            return -2 * t * t * t + 3 * t * t;
//        }
//    }
//}

namespace AbstractMath {
    public class MyString {
        public int length() {
            return 5;
        }
    }
    public class MathClass {
        public void b() {
            int x = a().length();
        }

        public MyString a() {
            return new MyString();
        }
    }
}