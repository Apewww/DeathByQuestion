package deathbyquestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    // ================================
    // 1. TEMA ALGORITMA (10 SOAL)
    // ================================
    public static List<Question> getAlgoritmaQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question(
            "Apa itu algoritma?",
            new String[]{"Langkah-langkah penyelesaian masalah", "Bahasa pemrograman", "Struktur data", "Framework"},
            0
        ));

        list.add(new Question(
            "Algoritma pencarian yang membagi ruang pencarian menjadi dua bagian adalah?",
            new String[]{"Linear Search", "Binary Search", "DFS", "BFS"},
            1
        ));

        list.add(new Question(
            "Time complexity dari Bubble Sort adalah?",
            new String[]{"O(n)", "O(n log n)", "O(n²)", "O(log n)"},
            2
        ));

        list.add(new Question(
            "Apa itu pseudocode?",
            new String[]{"Kode palsu", "Algoritma informal", "Syntax bahasa C", "Library Java"},
            1
        ));

        list.add(new Question(
            "Metode greedy algorithm memilih?",
            new String[]{"Solusi terbaik saat ini", "Solusi acak", "Semua kemungkinan", "Solusi terburuk"},
            0
        ));

        list.add(new Question(
            "Divide and conquer membagi masalah menjadi?",
            new String[]{"Masalah lebih besar", "Masalah lebih kecil", "Masalah acak", "Masalah sama"},
            1
        ));

        list.add(new Question(
            "Sorting tercepat pada data besar biasanya?",
            new String[]{"Bubble Sort", "Quick Sort", "Insertion Sort", "Selection Sort"},
            1
        ));

        list.add(new Question(
            "Dynamic Programming menggunakan teknik?",
            new String[]{"Backtracking", "Memoization", "Trial and error", "Rekursi murni"},
            1
        ));

        list.add(new Question(
            "Algoritma BFS menggunakan struktur data?",
            new String[]{"Stack", "Queue", "Tree", "Array"},
            1
        ));

        list.add(new Question(
            "Algoritma yang memastikan solusi optimal pada graph berbobot adalah?",
            new String[]{"Dijkstra", "BFS", "DFS", "Bubble Sort"},
            0
        ));

        return list;
    }


    // ================================
    // 2. TEMA STRUKTUR DATA (10 SOAL)
    // ================================
    public static List<Question> getStrukturDataQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question(
            "Struktur data yang mengikuti prinsip FIFO adalah?",
            new String[]{"Stack", "Queue", "Tree", "Graph"},
            1
        ));

        list.add(new Question(
            "Struktur data yang bekerja dengan prinsip LIFO adalah?",
            new String[]{"Queue", "Stack", "Linked List", "HashMap"},
            1
        ));

        list.add(new Question(
            "Linked list terdiri dari?",
            new String[]{"Array", "Node yang terhubung", "Stack", "Queue"},
            1
        ));

        list.add(new Question(
            "Binary tree maksimal memiliki berapa anak per node?",
            new String[]{"1", "2", "3", "Tidak terbatas"},
            1
        ));

        list.add(new Question(
            "Hash table menggunakan teknik?",
            new String[]{"Sorting", "Hashing", "Searching", "Parsing"},
            1
        ));

        list.add(new Question(
            "Graph dapat bersifat?",
            new String[]{"Berarah", "Tak berarah", "Keduanya benar", "Tidak ada"},
            2
        ));

        list.add(new Question(
            "Operasi push dilakukan pada?",
            new String[]{"Queue", "Stack", "Tree", "Graph"},
            1
        ));

        list.add(new Question(
            "Node tanpa anak pada tree disebut?",
            new String[]{"Root", "Leaf", "Parent", "Branch"},
            1
        ));

        list.add(new Question(
            "Big-O untuk akses array adalah?",
            new String[]{"O(n)", "O(1)", "O(log n)", "O(n²)"},
            1
        ));

        list.add(new Question(
            "Struktur data yang cocok untuk undo/redo?",
            new String[]{"Stack", "Queue", "Tree", "Array"},
            0
        ));

        return list;
    }


    // ================================
    // 3. TEMA PEMROGRAMAN WEB (10 SOAL)
    // ================================
    public static List<Question> getPemrogramanWebQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question(
            "Bahasa dasar untuk struktur halaman web adalah?",
            new String[]{"HTML", "CSS", "JavaScript", "PHP"},
            0
        ));

        list.add(new Question(
            "CSS digunakan untuk?",
            new String[]{"Logika web", "Desain tampilan", "Database", "Server"},
            1
        ));

        list.add(new Question(
            "JavaScript berjalan di?",
            new String[]{"Browser", "Database", "Server saja", "Compiler"},
            0
        ));

        list.add(new Question(
            "Framework front-end berikut yang populer adalah?",
            new String[]{"Laravel", "React", "Django", "Spring"},
            1
        ));

        list.add(new Question(
            "PHP termasuk bahasa?",
            new String[]{"Client-side", "Server-side", "Machine-level", "Assembly"},
            1
        ));

        list.add(new Question(
            "Database yang umum digunakan untuk web?",
            new String[]{"MySQL", "Illustrator", "Photoshop", "Premiere"},
            0
        ));

        list.add(new Question(
            "Tag HTML untuk membuat link?",
            new String[]{"<p>", "<a>", "<div>", "<span>"},
            1
        ));

        list.add(new Question(
            "HTTP adalah protokol untuk?",
            new String[]{"Transfer file", "Transfer web", "Komunikasi hardware", "Streaming video"},
            1
        ));

        list.add(new Question(
            "Node.js memungkinkan JavaScript berjalan di?",
            new String[]{"Client", "Server", "Database", "Compiler"},
            1
        ));

        list.add(new Question(
            "Framework CSS berikut ini adalah?",
            new String[]{"Laravel", "Tailwind", "Django", "Node"},
            1
        ));

        return list;
    }


    // ================================
    // METHOD UNTUK MENGAMBIL SEMUA SOAL
    // ================================
    public static List<Question> getQuestions() {
        List<Question> all = new ArrayList<>();
        all.addAll(getAlgoritmaQuestions());
        all.addAll(getStrukturDataQuestions());
        all.addAll(getPemrogramanWebQuestions());
        return all;
    }
}
