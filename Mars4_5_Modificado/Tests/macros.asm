.macro SyscallFork(%label)
	la $a0, %label
	li $v0, 18
	syscall	
	
.end_macro
.macro SyscallFork2(%endereco, %prioridade) 
    li $v0,21
    la $a0, %endereco
    la $a1, %prioridade
	syscall
.end_macro
.macro SyscallProcessChange
	li $v0, 19
	syscall
.end_macro
.macro SyscallProcessChange2
	li $v0, 37
	syscall
.end_macro
.macro SyscallProcessTerminate
	li $v0, 20
	syscall
.end_macro
.macro SyscallProcessTerminate2
	li $v0, 38
	syscall
.end_macro
.macro SyscallCreateSemaphore(%endereco_var)
    la $a0, %endereco_var
    li $v0,22
	syscall
.end_macro

.macro SyscallTerminateSemaphore(%endereco_var)
    la $a0, %endereco_var
    li $v0,23
	syscall
.end_macro

.macro SyscallDownSemaphore(%endereco_var)
    la $a0, %endereco_var
    li $v0,24
	syscall
.end_macro

.macro SyscallUpSemaphore(%endereco_var)
    la $a0, %endereco_var
    li $v0,25
	syscall
.end_macro
