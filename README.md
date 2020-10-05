# User Guide

```
               ....,       ,....
             .' ,,, '.   .' ,,, '.
              .`   `.     .`   `.
             : ..... :   : ..... :
             :`~'-'-`:   :`-'-'~`:
              `.~-`.'     `.~`'.'                              ___  __     __      __   __    
                ```   ___   ```                         /\      |  |__) | |__)    |  \ /  \ |  | |\ |  
                    ( . . )                            /~~\     |  |  \ | |       |__/ \__/ |/\| | \|

                     .._..                                   ___        __   __                         ___ 
                   .'     '.                           |\/| |__   |\/| /  \ |__) \ /    |     /\  |\ | |__  
                  `.~~~~~~~.`                          |  | |___  |  | \__/ |  \  |     |___ /~~\ | \| |___   
                    `-...-`
                                                  
               
```
## Table of Content
1. [Memory Lane](#memory-lane)  
1. [Setting up](#setting-up) 
1. [Features](#features)
1. [Usage](#usage)
    1. [`list` - List all tasks](#list---list-all-tasks)
    1. [`find` - Search for specific task that contains the search term](#find---search-for-specific-task-that-contains-the-search-term)
    1. [`todo` - Add a `todo` task to the list](#todo---add-a-todo-task-to-the-list)
    1. [`event` - Add an `event` task to the list](#event---add-an-event-task-to-the-list)
    1. [`deadline` - Add a `deadline` task to the list](#deadline---add-a-deadline-task-to-the-list)
    1. [`done` - Mark task as done](#done---mark-task-as-done)
    1. [`delete` - Delete a task](#delete---delete-a-task)
    1. [`clear` - Clear the list](#clear---clear-the-list)
    1. [`help` - Print help menu](#help---print-help-menu)
    1. [`bye` - Exit the program](#bye---exit-the-program)
1. [Command Summary](#command-summary)
<br/><br/><br/>
## Memory Lane 
A simple task tracker that helps you to remember all that things to do that you chose to forget.

Your second brain.
<br/><br/><br/>
## Setting up 
Prerequisites: JDK 11
1. Download `MemoryLane.jar` from [here](https://github.com/chocomango/ip/releases/download/v0.2/MemoryLane.jar).
1. Move the jar file into an empty folder.
1. Enter `WINDOWS + R` and type `cmd` to run the windows command prompt.
1. Setup the command prompt as follows:
    1. Right click the top of the `Command Prompt` window.    
    1. Select `Properties` and under the `Font` section, scroll down and select `NSimSun`.    
    1. Click `OK`.    
    1. Type the following command `chcp 65001` and hit `Enter`.
   
1. Use the `cd <directory_path>` command to navigate the directory to the folder where `MemoryLane.jar` is located at.
1. Type in `java -Dfile.encoding=UTF-8 -jar MemoryLane.jar` to run the program.


<br/><br/><br/>
## Features  
* Stores different types of task.
* Keep track of status of each task.
* Search for a specific task.
* Delete tasks that deemed useless.
* Save all the tasks into local memory.
* Load tasks from local memory.
<br/><br/><br/> 
## Usage 
### `list` - List all tasks 
This command prints the list of tasks added by user and loaded from local memory. 

Information in outcome:
- Type of the task - The letter in the first pair of square brackets.

        T - Todo
        E - Event
        D - Deadline
- Completion status -   Icon in the second pair of square brackets.
- Description of task - Text after the square brackets.
- Date and time of task* -  In the round brackets .

Only for `Events`* and `Deadlines`*

Syntax: `list`  

Example: `list`

Expected outcome:
```
Here you go...
1. [D][✓] submit assignment (by: Sep 26 2020 23:59)
2. [E][✘] family dinner (at: Oct 10 2020 18:30)
3. [T][✘] buy groceries 
4. [E][✘] dinner with friends (at: Oct 15 2020 19:00)
5. [D][✘] submit SEP application (by: Oct 09 2020 23:59)
6. [T][✘] drink bbt 
You have 6 items.
```

<br/><br/><br/>
### `find` - Search for specific task that contains the search term 
This command searches for all tasks that matches with the search term.

Syntax: `find <search_term>`  

Required Option:
- `search_term` - Search term.

Information in outcome:
- Numbers in the bracket are the `task number` that can be used in `done` and `delete` command.

Example: `find dinner`

Expected outcome:
```
Here are the matching tasks in your list:
	1. (2) [E][✘] family dinner (at: Oct 10 2020 18:30)
	2. (4) [E][✘] dinner with friends (at: Oct 15 2020 19:00)
```
<br/><br/><br/>
### `todo` - Add a `todo` task to the list 
This command creates a new entry of todo task.

Syntax: `todo <task_description>` 
 
Required Option:
- `task_description` - Description of the task.

Example: `todo buy groceries`

Expected outcome:
```
New memory entry: [T][✘] buy groceries 
You have 6 items.
```
<br/><br/><br/>
### `event` - Add an `event` task to the list 
This command creates a new entry of event.

Syntax: `event <task_description> /at <date_time>`  

Required Option:
- `task_description` - Description of the event.
- `date_time` - Date and time of the event.

Support format for `date_time`: `YYYY-MM-DD HHMM`

Example: `event family dinner /at 2020-10-10 1830`

Expected outcome:
```
New memory entry: [E][✘] family dinner (at: Oct 10 2020 18:30)
You have 7 items.
```

<br/><br/><br/>
### `deadline` - Add a `deadline` task to the list 
This command creates a new entry of deadline

Syntax: `deadline <task_description> /by <date_time>`  

Required Option:
- `task_description` - Description of the deadline.
- `date_time` - Date and time of the deadline.

Support format for `date_time`: `YYYY-MM-DD HHMM`

Example: `deadline submit assignment /by 2020-09-26 2359`

Expected outcome:
```
New memory entry: [D][✘] submit assignment (by: Sep 26 2020 23:59)
You have 7 items.
```

<br/><br/><br/>
### `done` - Mark task as done 
This command marks a task as completed.

Syntax: `done <task_number>`

Required Option:
- `task_number` - Task number (Same as the order of task creation).

Example: `done 2`

Expected outcome:
```
Okay. [E][✓] dinner with friends (at: Oct 15 2020 19:00) completed.
```

<br/><br/><br/>
### `delete` - Delete a task 
This command removes a task from the list.

Syntax: `delete <task_number>  `

Required Option:
- `task_number` - Task number (Same as the order of task creation).

Example: `delete 5`

Expected outcome:
```
Okay. [T][✘] buy groceries  deleted.
```

<br/><br/><br/>
### `clear` - Clear the list 
This command removes all task from the list.

Syntax: `clear`


Example: `clear`

Expected outcome:
```
Memory cleared.
```

<br/><br/><br/>
### `help` - Print help menu
This command prints the help menu.

Syntax: `help`  

Example: `help`

Expected outcome:
```
List all tasks.
Usage: list

Clear all tasks.
Usage: clear

Exit the program.
Usage: bye

Mark task as done.
Usage: done <task_number>

Delete a task.
Usage: delete <task_number>

Add a todo task to the list.
Usage: todo <task_description>

Add a deadline task to the list.
Usage: deadline <task_description> /by <date_time>
Supported format for date_time: YYYY-MM-DD HHMM

Add an event task to the list..
Usage: event <task_description> /at <date_time>
Supported format for date_time: YYYY-MM-DD HHMM

Search for specific task that contains the search term..
Usage: find <search_term>

```

<br/><br/><br/>
### `bye` - Exit the program 
This command exits the program

Syntax: `bye`  

Example: `bye`

Expected outcome:
```
Until next time...

Process finished with exit code 0
```
<br/><br/><br/>
## Command Summary 
![Command Summary](https://i.imgur.com/PXrtlfK.png)