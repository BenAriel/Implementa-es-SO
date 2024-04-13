.include "macros.asm"

.data
.text             
      #criação dos processos com prioridade
SyscallFork2(Programa1,1)
	SyscallFork2(Programa2,2)
	
	#escalonando o primeiro processo
SyscallProcessChange2
	
Programa1:					
		addi $s1, $zero, 1 # valor inicial do contador
		addi $s2, $zero, 10 # valor limite do contador
	loop1: 	addi $s1, $s1, 1
		beq $s1, $s2, fim1
j loop1
	fim1:	SyscallProcessTerminate2

Programa2: 
		addi $s3, $zero, -1 # valor inicial do contador
		addi $s4, $zero, -10 # valor limite do contador
	loop2: 	addi $s3, $s3, -1
		beq $s3, $s4, fim2
		j loop2
	fim2:	SyscallProcessTerminate2
