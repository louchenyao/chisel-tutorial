// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._

// Problem:
//
// Implement a vending machine using 'when' states.
// 'nickel' is a 5 cent coin
// 'dime'   is 10 cent coin
// 'sOk' is reached when there are coins totalling 20 cents or more in the machine.
// The vending machine should return to the 'sIdle' state from the 'sOk' state.
//
class VendingMachine extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = Enum(5)
  val state = RegInit(sIdle)

  // Implement below ----------

  when (state === sIdle & io.nickel) {
    state := s5
  }.elsewhen (state === s5 & io.nickel) {
    state := s10
  }.elsewhen (state === s10 & io.nickel) {
    state := s15
  }.elsewhen (state === s15 & io.nickel) {
    state := sOk
  }

  when (state === sIdle & io.dime) {
    state := s10
  }.elsewhen (state === s5 & io.dime) {
    state := s15
  }.elsewhen (state === s10 & io.dime) {
    state := sOk
  }.elsewhen (state === s15 & io.dime) {
    state := sOk
  }

  when (state === sOk) {
    state := sIdle
  }

  // Implement above ----------

  io.valid := (state === sOk)
}
