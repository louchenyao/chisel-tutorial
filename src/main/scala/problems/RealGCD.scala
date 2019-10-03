// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  val working = RegInit(false.B)
  val x = Reg(UInt(8.W))
  val y = Reg(UInt(8.W))
  io.in.ready := !working

  when (io.in.valid & !working) {
    x := io.in.bits.a
    y := io.in.bits.b
    working := true.B
  }

  when (working & !(y === 0.U)) {
    x := y
    y := x % y
  }

  io.out.bits := x
  io.out.valid := y === 0.U & working

  when (io.out.valid & working) {
    working := false.B
  }
}
