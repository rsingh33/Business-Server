Business-Server
===============

Java Back-end Server for financial Trading and Exchange

This is a multithreaded business server which I developed during my internship at Dojima LLC. This Server offers basic login log out functionality as well as to to exchange business messages such a set stock price purchase sell no interest net buy net sell etc using Json.

Tools and Skills used : Java | Multithreading | TC/IP |Eclipse |Json | Gson |

How to run it :

Just run the server which will start in an infinite loop

Now if you are testing from same machine goto terminal and telnet it on port 10000. Eg telnet localhost 10000:

Design doc :

The server kick starts opening at port no 10000 by reading a temporary database file called database.txt in memory.

First Message is Login message

Suppose If a user wants to log in via his credentials the server expects three things (username, password, member id) in json format, though the third part i.e the member id is not needed for login its just an extra parameter. Eg of login message:

{"Message":"login", "values":{"userid":"user1","password":"pass1","memberid":"member1"}}

Second type of message handled by the server is setPrice

Server on receiving the price display the current price to everyone .Message type eg is: On receiving a new price the server updates the net interest(explained below) to 0.

{Message:"setPrice", setPrice:"1000"}

Third type of message is buy and sell

These two messages are handled by the server in order to update what is the net interest right now a buy will add it to net interest and buy will subtract it from net interest. Sample messages: {Message:"interest", sell:"20"} {Message:"interest", buy:"10"}

Third type of message is logout

Final type message is logout

when the server receives the logout message it closes the connection with client and updates other clients that somebody has left.

{Message:"logout", logout:"user1"}
