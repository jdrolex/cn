#server code
import socket

server_socket=socket.socket()
print('Socket Created')
server_socket.bind(('localhost',589))
server_socket.listen(4)
print('Socket is now listening')

while True:
    conn,addr=server_socket.accept()
    data=conn.recv(1024).decode('utf-8')
    print('Connected to',addr)
    print('Data:',data)
    conn.send(bytes('Welcome to local mail server','utf-8'))
    conn.close()

#client code
import socket
client_socket=socket.socket()
client_socket.connect(('localhost',587))
name=input('Enter the data:')
data=name.encode('utf-8')
client_socket.send(data)
received_data=client_socket.recv(1024).decode('utf-8')
print(received_data)
