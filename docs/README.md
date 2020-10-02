# User Guide

```
               ....,       ,....
             .' ,,, '.   .' ,,, '.
              .`   `.     .`   `.
             : ..... :   : ..... :
             :`~'-'-`:   :`-'-'~`:
              `.~-`.'     `.~`'.'
                ```   ___   ```
                    ( . . )

                     .._..
                   .'     '.
                  `.~~~~~~~.`
                    `-...-`
```
## Features 
### Memory Lane~
Remeber things for you

### Type of Tasks
Add tasks that suits your needs. Types of task includes:
- **Todo**: Specifies tasks that needs to be done.
- **Deadline**: Specifies tasks that have deadlines.
- **Event**: Specifies tasks that happen on a certain date.

### Search
Search tasks using keyword easily with a single command.

### Persistent Data
Changes made to the task list are saved to the local storage and loaded when the program starts.

### Multi-Platform
This program is supported across multiple platforms including but not limited to **Windows**, **Linux** and **macOS**.

## Usage
### `list` - List the tasks available

### `delete ` - Delete a task from the task list

This command will attempt to delete a task.

Required Option:


Example of usage: 
`delete 5`

Expected outcome:
```

	[E][✘] meeting (at: Jan 05 2020 18:00)
```
*- Task is successfully deleted.*
```

```
```

```
*- Specified task does not exist.*


### `exit` - Exit the program

This command will cause the program to save the current list of tasks and exit.

Example of usage: 
`exit`

Expected Outcome:
```

```

### Unknown Command
When an unknown command is entered, the program outputs the following message and returns to wait for input.

Example:

