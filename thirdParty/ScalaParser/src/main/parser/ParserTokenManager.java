/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package main.parser;

/** Token Manager. */
public class ParserTokenManager implements ParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7ffffffff000L) != 0L)
         {
            jjmatchedKind = 9;
            return -1;
         }
         return -1;
      case 1:
         if ((active0 & 0x7ffffffff000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x7fffffb7f000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 3:
         if ((active0 & 0x73ffff378000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 4:
         if ((active0 & 0x71efff220000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 5:
         if ((active0 & 0x214f6a020000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 6:
         if ((active0 & 0x210e42020000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 7:
         if ((active0 & 0xc40020000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 9;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjStopAtPos(0, 63);
      case 34:
         return jjStopAtPos(0, 57);
      case 40:
         return jjStopAtPos(0, 7);
      case 41:
         return jjStopAtPos(0, 8);
      case 42:
         return jjStopAtPos(0, 58);
      case 43:
         return jjStopAtPos(0, 61);
      case 44:
         return jjStopAtPos(0, 49);
      case 45:
         return jjStopAtPos(0, 60);
      case 46:
         return jjStopAtPos(0, 48);
      case 58:
         return jjStopAtPos(0, 56);
      case 59:
         return jjStopAtPos(0, 51);
      case 60:
         return jjMoveStringLiteralDfa1_0(0x800000000000000L, 0xcL);
      case 61:
         jjmatchedKind = 47;
         return jjMoveStringLiteralDfa1_0(0x4000000000000L, 0x0L);
      case 62:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2L);
      case 64:
         return jjStopAtPos(0, 54);
      case 91:
         return jjStopAtPos(0, 52);
      case 93:
         return jjStopAtPos(0, 53);
      case 95:
         return jjStopAtPos(0, 55);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x40000000L, 0x0L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x3001000000L, 0x0L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x401000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x10000100000L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x80082000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x200a0000L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x40000L, 0x0L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x400000000000L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x40000000000L, 0x0L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x4800000000L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x200600000000L, 0x0L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x8000000L, 0x0L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x110000000L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x8004818000L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x6000L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x20000200000L, 0x0L);
      case 121:
         return jjMoveStringLiteralDfa1_0(0x100000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 5);
      case 124:
         return jjStopAtPos(0, 64);
      case 125:
         return jjStopAtPos(0, 6);
      case 126:
         return jjStopAtPos(0, 62);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 37:
         if ((active1 & 0x8L) != 0L)
            return jjStopAtPos(1, 67);
         break;
      case 45:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(1, 59);
         break;
      case 58:
         if ((active1 & 0x2L) != 0L)
            return jjStopAtPos(1, 65);
         else if ((active1 & 0x4L) != 0L)
            return jjStopAtPos(1, 66);
         break;
      case 62:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 50);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x601001046000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x4040000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x40108001000L, active1, 0L);
      case 102:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(1, 19);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x4210000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x120082000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000100000L, active1, 0L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x20020000L, active1, 0L);
      case 111:
         if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(1, 22);
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x8600800000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0xa100000000L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x100800000000L);
      case 102:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(2, 12);
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x200210000L);
      case 106:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000000L);
      case 108:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(2, 13);
         break;
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x82000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x30028000L);
      case 114:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(2, 14);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStopAtPos(2, 43);
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x1040100000L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x430009000000L);
      case 119:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(2, 42);
         break;
      case 121:
         if ((active0 & 0x800000L) != 0L)
            return jjStopAtPos(2, 23);
         break;
      case 122:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0, 0L);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, 0L);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x82000000L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x400001000000L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(3, 15);
         else if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(3, 20);
         else if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(3, 36);
         return jjMoveStringLiteralDfa4_0(active0, 0x14010000000L);
      case 104:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStopAtPos(3, 41);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000000L);
      case 107:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x100100220000L);
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x24000000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 115:
         if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(3, 16);
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x440000000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000L);
      case 118:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 121:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(3, 18);
         break;
      default :
         break;
   }
   return jjStartNfa_0(2, active0, 0L);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x200200000000L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000L);
      case 100:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(4, 44);
         break;
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(4, 21);
         return jjMoveStringLiteralDfa5_0(active0, 0x500000000L);
      case 104:
         if ((active0 & 0x1000000L) != 0L)
            return jjStopAtPos(4, 24);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStopAtPos(4, 46);
         break;
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000L);
      case 108:
         if ((active0 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000000L);
      case 114:
         if ((active0 & 0x10000000L) != 0L)
            return jjStopAtPos(4, 28);
         return jjMoveStringLiteralDfa5_0(active0, 0x868000000L);
      case 115:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStopAtPos(4, 37);
         break;
      case 116:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(4, 39);
         break;
      case 119:
         if ((active0 & 0x4000000L) != 0L)
            return jjStopAtPos(4, 26);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000000L);
      case 99:
         return jjMoveStringLiteralDfa6_0(active0, 0x400020000L);
      case 100:
         if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(5, 32);
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000000L);
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000000L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000000L);
      case 110:
         if ((active0 & 0x8000000L) != 0L)
            return jjStopAtPos(5, 27);
         break;
      case 116:
         if ((active0 & 0x20000000L) != 0L)
            return jjStopAtPos(5, 29);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(5, 38);
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x40000000L);
      case 100:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000L);
      case 101:
         if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(6, 33);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStopAtPos(6, 45);
         break;
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x20000L);
      case 115:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(6, 40);
         break;
      case 116:
         return jjMoveStringLiteralDfa7_0(active0, 0x400000000L);
      case 121:
         if ((active0 & 0x2000000L) != 0L)
            return jjStopAtPos(6, 25);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(7, 35);
         return jjMoveStringLiteralDfa8_0(active0, 0x400000000L);
      case 116:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(7, 17);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStopAtPos(7, 30);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(8, 34);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 10)
                        kind = 10;
                  }
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 11)
                        kind = 11;
                  }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L && kind > 10)
                     kind = 10;
                  break;
               case 2:
                  if ((0x3fe000000000000L & l) != 0L && kind > 11)
                     kind = 11;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     kind = 9;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\173", "\175", "\50", "\51", null, null, null, 
"\144\145\146", "\166\141\154", "\166\141\162", "\164\171\160\145", "\164\150\151\163", 
"\151\155\160\154\151\143\151\164", "\154\141\172\171", "\151\146", "\145\154\163\145", "\167\150\151\154\145", 
"\144\157", "\164\162\171", "\143\141\164\143\150", "\146\151\156\141\154\154\171", 
"\164\150\162\157\167", "\162\145\164\165\162\156", "\163\165\160\145\162", 
"\151\155\160\157\162\164", "\141\142\163\164\162\141\143\164", "\146\151\156\141\154", 
"\163\145\141\154\145\144", "\160\162\151\166\141\164\145", "\160\162\157\164\145\143\164\145\144", 
"\157\166\145\162\162\151\144\145", "\143\141\163\145", "\143\154\141\163\163", "\157\142\152\145\143\164", 
"\164\162\141\151\164", "\145\170\164\145\156\144\163", "\167\151\164\150", "\156\145\167", 
"\146\157\162", "\171\151\145\154\144", "\160\141\143\153\141\147\145", 
"\155\141\164\143\150", "\75", "\56", "\54", "\75\76", "\73", "\133", "\135", "\100", "\137", "\72", 
"\42", "\52", "\74\55", "\55", "\53", "\176", "\41", "\174", "\76\72", "\74\72", 
"\74\45", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffffffffffffe1L, 0xfL, 
};
static final long[] jjtoSkip = {
   0x1eL, 0x0L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[3];
private final int[] jjstateSet = new int[6];
protected char curChar;
/** Constructor. */
public ParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 3; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
