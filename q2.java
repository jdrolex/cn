#server code
import socket

def send_file(conn, filename):
    try:
        with open(filename, 'rb') as file:
            data = file.read()
            conn.sendall(data)
    except FileNotFoundError:
        print("File not found")

def main():
    host = 'localhost'
    port = 12345

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((host, port))
        server_socket.listen(1)
        print(f"Server listening on {host}:{port}")

        conn, addr = server_socket.accept()
        print(f"Connected to {addr}")

        filename = 'example.txt'
        send_file(conn, filename)

if __name__ == "__main__":
    main()
#client code
import socket

def receive_file(server_host, server_port, filename):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((server_host, server_port))
        data = client_socket.recv(1024)
        with open(filename, 'wb') as file:
            file.write(data)
        print(f"File received and saved as {filename}")

def main():
    server_host = 'localhost'
    server_port = 12345
    filename = 'received_file.txt'

    receive_file(server_host, server_port, filename)

if __name__ == "__main__":
    main()
