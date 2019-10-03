// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

import scala.math.max

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------

    val a = rnd.nextInt(1 << c.io.in0.getWidth)
    val b = rnd.nextInt(1 << c.io.in1.getWidth)

    poke(c.io.in0, a)
    poke(c.io.in1, b)
    step(1)
    expect(c.io.out, max(a, b))

    // Implement above ----------
  }
}
