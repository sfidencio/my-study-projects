# Shell Script for DevOps

## 🚀 Introduction
Shell Script is fundamental for task automation in DevOps environments, enabling routine creation, tool integration, and increased productivity.

---

## 📑 Table of Contents
- [Basic Shell Script Syntax](#basic-shell-script-syntax)
- [Variables and Data Types](#variables-and-data-types)
- [Control Structures (if, for, while)](#control-structures-if-for-while)
- [Functions and Modularization](#functions-and-modularization)
- [File and Directory Manipulation](#file-and-directory-manipulation)
- [Common Task Automation](#common-task-automation)
- [Integration with other tools (e.g., Docker, Git)](#integration-with-other-tools-eg-docker-git)
- [Error Handling and Logging](#error-handling-and-logging)
- [Security Best Practices](#security-best-practices)
- [Command Execution, stdin, stdout, stderr](#command-execution-stdin-stdout-stderr)
- [Shell Operators (`;`, `&&`, `&`, `>`, `>>`, `2>`, `2>&1`, `/dev/null`)](#command-execution-stdin-stdout-stderr)
- [stdin, stdout, stderr](#command-execution-stdin-stdout-stderr)

---

## ℹ️ Command Execution, stdin, stdout, stderr

### Use of `;` and `&&`
- `;` allows you to execute multiple commands in sequence, regardless of the success or failure of the previous one.
  ```sh
  command1; command2
  ```
- `&&` runs the next command only if the previous one succeeds (exit code 0).
  ```sh
  command1 && command2
  ```

> 🧐 **How does Linux know if a command succeeded or failed?**  
> After executing any command, Linux returns an **exit code**, accessible via the special variable `$?`.  
> - `0` means success  
> - Any other value indicates an error  
> Example:
> ```sh
> command1
> echo $?
> ```

### Difference between `;` and `&&`
- `;` waits for the previous command to finish before starting the next, regardless of the result.
- `&&` only runs the next command if the previous one succeeds.

### Other useful operators

- `&` runs the command in the background, freeing the terminal for other commands.
  ```sh
  command1 &
  ```

- `>` redirects standard output (stdout) to a file, overwriting it.
  ```sh
  command1 > file.txt
  ```

- `>>` appends standard output (stdout) to a file.
  ```sh
  command1 >> file.txt
  ```

- `2>` redirects standard error (stderr) to a file.
  ```sh
  command1 2> errors.txt
  ```

- `2>&1` redirects stderr to the same destination as stdout.
  ```sh
  command1 > output.txt 2>&1
  ```

- `/dev/null` discards any output sent to it (useful to ignore output).
  ```sh
  command1 > /dev/null 2>&1
  ```

### stdin, stdout, stderr
- **stdin**: default input stream, usually the keyboard.
- **stdout**: default output stream, usually the terminal/screen.
- **stderr**: error output stream, separate from stdout.

#### Examples
```sh
echo "Hello" > output.txt    # Redirects stdout to a file
cat < input.txt              # Uses input.txt as stdin
ls notfound 2> error.txt     # Redirects stderr to a file
ls notfound > /dev/null 2>&1 # Discards stdout and stderr
```

---

## 📚 Useful Resources
- [Advanced Bash-Scripting Guide](https://tldp.org/LDP/abs/html/)
- [ShellCheck](https://www.shellcheck.net/)
- [Bash Official Documentation](https://www.gnu.org/software/bash/manual/bash.html)

---

## 🎯 Conclusion
Mastering Shell Script is essential for any DevOps professional, making daily process automation and integration easier.
