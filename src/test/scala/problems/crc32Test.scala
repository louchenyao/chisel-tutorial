package problems

import chisel3._
import chisel3.iotesters.PeekPokeTester

class CRC32Test(c: CRC32) extends PeekPokeTester(c) {
    def init() {
        poke(c.io.init, true)
        step(1)
        expect(c.io.out, 0xffffffffL.U(32.W))
    }
    def feed(data: Byte) {
        poke(c.io.init, false)
        poke(c.io.curByte, data)
        step(1)
    }
    init()
    feed(0x20)
    feed(0x6a)
    feed(0x12)
    feed(0x34)
    expect(c.io.out, 0x6289454DL.U(32.W))
}