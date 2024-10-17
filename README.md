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

!!!!! Welcome to cron parser !!!!!

minute        0 15 30 45
hour          0
day of month  1 4 7 10 13 16 19 22 25 28 31
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   3 4 5 6 7
command       /usr/bin/find



