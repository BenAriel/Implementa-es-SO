.macro SyscallFork(%label)
	la $a0, %label
	li $v0, 18
	syscall	
.end_macro 
.macro SyscallProcessChange
	li $v0, 19
	syscall
.end_macro
.macro SyscallProcessTerminate
	li $v0, 20
	syscall
.end_macro