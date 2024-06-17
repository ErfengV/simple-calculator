# 简易的表达式计算器

## 简介

实现了一个简易的计算器，
能够解析和计算包含加、减、乘、除、模运算、幂运算以及基本数学函数（如 `sin`, `cos`, `tan`, `log`, `sqrt`, `exp`）的表达式。
它演示了如何使用词法分析器和语法分析器来解析和计算数学表达式。

## 目录

- [简介](#简介)
- [目录](#目录)
- [功能](#功能)
- [使用方法](#使用方法)
- [代码结构](#代码结构)
- [语法规则](#语法规则)
- [示例](#示例)


## 功能

- 支持基本的四则运算：加法（`+`）、减法（`-`）、乘法（`*`）、除法（`/`）
- 支持模运算：`%`
- 支持幂运算：`^`
- 支持括号来改变运算顺序
- 支持基本数学函数：`sin(x)`, `cos(x)`, `tan(x)`, `log(x)`, `sqrt(x)`, `exp(x)`

## 使用方法

1. 克隆或下载本项目代码：
   ```bash
   git clone https://github.com/Erfeng_V/simple-calculator.git

2. 编译 Java 源文件：
   ```bash
   javac Calculator.java

3. 运行程序：
   ```bash
   java Calculator

4. 输入一个数学表达式并按回车键即可计算结果;输入“end”按回车键可以退出程序

## 代码结构

- `Token.java`：定义了词法分析器生成的 Token 类型。
- `Lexer.java`：实现了词法分析器，用于将输入的字符串分割成 Token。
- `Parser.java`：实现了语法分析器，用于解析 Token 并计算结果。
- `Calculator.java`：程序的主入口，负责读取输入、调用 Lexer 和 Parser 并输出结果。

## 语法规则
该计算器使用的语法规则如下：

     grammar:
     expr    -> term ( ( "+" | "-" ) term )*
     term    -> factor ( ( "*" | "/" ) factor )*
     factor  -> base ( "^" base )*
     base    -> NUMBER | "-" base | "(" expr ")"

## 示例

     Enter an expression: 1+2^3+4-3*2
     Result: 7.0

     Enter an expression: (8*2+4)/2
     Result: 10.0

     Enter an expression: sqrt(4)+1+(9-4)^2
     Result: 28.0

     Enter an expression: 5%2*1-2
     Result: -1.0

     Enter an expression: -2*9+cos(45)
     Result: -17.47467801118227

     Enter an expression: sin(9)
     Result: 0.4121184852417566

     Enter an expression: exp(1)+1
     Result: 3.718281828459045

     Enter an expression: end
