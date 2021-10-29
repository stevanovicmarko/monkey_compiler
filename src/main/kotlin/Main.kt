
import lexer.Lexer

import parser.Parser
import vm.Compiler
import vm.VM

fun main() {
    val input = """
        let globalNum = 10;
        let sum = fn(a, b) {
            let c = a + b;
            c + globalNum;
        };
        let outer = fn() {
            let x = [1, 4, 5];
            sum(1, 2) + sum(3, 4) + globalNum - len(x);
        };
        outer() + globalNum;
        """
    val lexer = Lexer(input)
    val parser = Parser(lexer)
    val program = parser.parseProgram()
    val compiler = Compiler()
    compiler.compile(program)
    println(compiler)
    val vm  = VM(compiler.currentInstructions, compiler.constants)
    vm.run()
}