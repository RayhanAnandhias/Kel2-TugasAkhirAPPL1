# Tugas Akhir APPL1 Kelompok 2
Repositori ini berisi source code hasil pengerjaan Tugas Akhir Mata Kuliah APPL 1 Praktikum.

Source code original kami fork dari repositori https://github.com/MattJett/Travel-Booking-System

## Data Anggota Kelompok

No | Nama | NIM
------------ | ------------- | -------------
1 | Dewanto Joyo Pramono | 181524005
2 | Muhammad Fadhil | 181524020
3 | Rayhan Azka A.P | 181524028

## Deskripsi Tugas
1. Cari kasus program yang berkisar 500 baris untuk bagian Layer bisnis (tidak berhubungan dg UI/API/Database) - Seperti kasus ATM di semester 2
2. Lakukan reverse engineering dg pendekatan BDD dan DDD
3. Lakukan refactoring dan lengkapi dg test-automation(unit testing, mock)
4. Pikirkan 5 pengembangan fitur yg bernilai dan sesuaikan program Anda agar mudah dikembangkan dg mengikuti: 
    - Solid Principles
    - Design Pattern
    - Aspect Oriented
5. Lakukan implementasi pengembangan fitur

## Mekanisme Pengerjaan
1. Repository ada GIT
2. Class Diagram yg mudah dibaca (dipecah2 jadi 1 halaman yg mudah dibaca, hindari detail)
3. Historikan class diagram dan beri penjelasan untuk tiap perubahan

# Travel-Booking-System
Program Travel Booking System ini dibuat untuk mempermudah booking tiket mulai dari pesawat, kereta, hingga kapal pesiar. Program ini memungkinkan pengguna untuk melakukan booking terhadap berbagai macam tiket dan melihat kursi mana yang telah dibooking. Dalam program ini terdapat menu untuk admin serta menu untuk user. Menu admin digunakan untuk menambahkan beberapa stasiun, bandara, hingga jadwal penerbangan yang nantinya digunakan untuk sistem booking ini. Berikut merupakan link google docs hasil dari reverse engineering yang telah kami lakukan dengan pendekatan BDD dan DDD.
https://docs.google.com/document/d/1QCvR--qWfC50C9qJLy1DP0DbCuLFFNex4fJvgNQG9GM/edit?usp=sharing

## Pengembangan Fitur
Untuk beberapa fiturnya dari program itu sendiri belum semuanya sudah terimplementasi sehingga kami berencana untuk menyelesaikan fitur tersebut dan menambahkan beberapa fitur baru sebagai berikut.
### 1.Fitur Menambah Stasiun (Admin)
-----------
Fitur admin untuk menambahkan stasiun baru ke dalam travel booking system. Kami menambahkan menambahkan class station yang extends terhadap abstract class port.
### 2.Fitur Menambah Kereta (Admin)
-----------
Fitur admin untuk menambahkan kereta baru ke dalam travel booking system.Kami menambahkan railroad yang extends terhadap abstract class Carrier.
### 3.Fitur Menambah Train Trip (Admin)
-----------
Fitur admin untuk menambahkan rute perjalanan kereta ke dalam travel booking system.
### 4.Fitur Menambah Train Trip Section (Admin)
-----------
Fitur admin untuk menambahkan bagian rute perjalanan kereta dimulai dari data stasiun departure dan arrival, nama kereta, tanggal, kelas kereta, dan harga.  
### 5.Fitur Booking Train Trip
-----------
Fitur untuk menambahkan data pemesanan pengguna kereta ke dalam travel booking system. 

## Diagram Class History
![ClassDiagram1](https://github.com/RayhanAnandhias/Kel2-TugasAkhirAPPL1/blob/master/classDiagram1.jpg?raw=true)
Diagram di atas merupakan class diagram dari program sebelum penambahan sistem booking tiket kereta dimana hanya terdapat sistem booking tiket pesawat dan belum seluruh fiturnya telah diimplementasikan.

![ClassDiagram2](https://github.com/RayhanAnandhias/Kel2-TugasAkhirAPPL1/blob/master/FixUML.jpg?raw=true)
Diagram tersebut merupakan class diagram setelah penambahan sistem booking tiket kereta dan telah mengimplementasikan fitur-fitur yang sebelumnya belum selesai. Namun class diagram tersebut belum mengimplementasikan sistem booking tiket kapal pesiar secara keseluruhan.

Penjelasan Penggunaan Program
----------
* Aplikasi dapat dijalankan dengan menjalankan UI.java pada package UserInterface.
* Untuk menu itu sendiri dapat dilihat setelah menjalankan UI.java
* Untuk menjalankan unit test dapat dilakukan dengan cara menjalankan UnitTest.java dalam (default package)
