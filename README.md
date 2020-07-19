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
Program Travel Booking System ini dibuat untuk mempermudah booking tiket mulai dari pesawat, kereta, hingga kapal pesiar.
Dalam program ini terdapat menu untuk admin serta menu untuk user. Menu admin digunakan untuk menambahkan beberapa stasiun, bandara, hingga jadwal penerbangan yang nantinya digunakan untuk sistem booking ini. Untuk beberapa fiturnya itu sendiri belum semuanya sudah diimplementasikan. Dengan begitu kami melakukan pengembangan fitur berdasarkan fitur yang belum diimplementasikan tersebut.
## Pengembangan Fitur
### 1.Fitur Menambah Stasiun (Admin)
-----------
### 2.Fitur Menambah Kereta (Admin)
-----------
### 3.Fitur Menambah Train Trip (Admin)
-----------
### 4.Fitur Menambah Train Trip Section (Admin)
-----------
### 5.Fitur Booking Train Trip
-----------

## Diagram Class History
![ClassDiagram1](https://github.com/RayhanAnandhias/Kel2-TugasAkhirAPPL1/blob/master/classDiagram1.jpg?raw=true)
Diagram di atas merupakan class diagram dari program sebelum penambahan sistem booking tiket kereta dimana hanya terdapat sistem booking tiket pesawat dan belum seluruh fiturnya telah diimplementasikan.
![ClassDiagram2](https://github.com/RayhanAnandhias/Kel2-TugasAkhirAPPL1/blob/master/FixUML.jpg?raw=true)
Diagram tersebut merupakan class diagram setelah penambahan sistem booking tiket kereta dan telah mengimplementasikan fitur-fitur yang sebelumnya belum selesai. Namun class diagram tersebut belum mengimplementasikan sistem booking tiket kapal pesiar secara keseluruhan.
DESCRIPTION
-----------
Academic Project from Spring Quarter 2018, CSCD 349 - Design Patterns
Collaborative app with Tony Kolstee. Designed to read a trip manifest text file formatted in a specific way (parser designed around this format), then display trip data, allow the user to add new data to trip manifest and build trips off this data. Ability to export newly created trips in console appened back into the original text file or to a new one. Able to deliver prices for trips based on certain seating conditions user selects.

HOW TO RUN
----------
Project created in IntelliJ. 
* App can be launched from path, "Travel Booking System - Console App/src/UserInterface/UI.java".
* Main entry point at "UI.java". 
* During program launch, select menu option, "1: Work with Airline System".
* In AIR System Menu; if you want to load a premade trip manifest, select option, "1: Read input file" and when prompted to "Enter File Name", use, "AMSin.txt" or "altAMSin.txt" to load data.
* App can be run from "Tester.java". Standard conditional tester.

KNOWN BUGS
----------
No known bugs

NON-FUNCTIONING FEATURES
------------------------

* Rail and Cruise Ship system not fully implemented
* Not all Admin features enabled

PLANNED DESIGN UPDATES
----------------------
* If file, "AMSin.txt" is read in and then a duplicate airline or port entered causes intended error messages to display. I need to make it so that duplicated entries are just ignored. Error messages look too negative for user experience.
* Would love to convert this to a GUI app, might do in a future course or on free time as a challenge.
