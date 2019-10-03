// See LICENSE.txt for license details.
package hello

import chisel3._
import chisel3.iotesters.{PeekPokeTester, Driver}

class Hello(w: Int) extends Module {
  val io = IO(new Bundle {
    val in1 = Input(UInt(w.W))
    val in2 = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })
  io.out := io.in1 + io.in2
}

class HelloTests(c: Hello) extends PeekPokeTester(c) {
  poke(c.io.in1, 42)
  poke(c.io.in2, 7)
  step(1)
  expect(c.io.out, 49)
}

object Hello {
  def main(args: Array[String]): Unit = {
    if (!Driver(() => new Hello(8))(c => new HelloTests(c))) System.exit(1)
  }
}
