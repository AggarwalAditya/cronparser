 Cron Expression Parser

A command line application or script which parses a cron string and expands each field
to show the times at which it will run.

Considered the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command.

The input will be on a single line.
The cron string will be passed to your application as a single argument.
~$ ./cronparser.sh "*/15 0 1,15 * 1-5 /usr/bin/find"

The output will be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.
For example, the following input argument:
*/15 0 */3 * 3-7 /usr/bin/find

Should yield the following output:






<img width="756" alt="Screenshot 2024-10-17 at 4 38 23 PM" src="https://github.com/user-attachments/assets/6468a5c9-0a51-4039-b545-a8069114fdcd">

