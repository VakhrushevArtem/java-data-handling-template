package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {
    private int countFiles = 0;
    private int countDirs = 0;

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            return 0;
        }
        for (File fileTemp : files) {
            if (fileTemp.isFile()) {
                countFiles++;
            } else {
                countFilesInDirectory(fileTemp.getPath());
            }
        }
        return countFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            countDirs++;
            File[] files = file.listFiles();
            if (files != null) {
                for (File fileTemp : files) {
                    countDirsInDirectory(fileTemp.getPath());
                }
            }
        }
        return countDirs;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File[] files = new File(from).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.toString().endsWith(".txt")) {
                    try {
                        Files.copy(file.toPath(), new File(to).toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        String fullPath = path + "/" + name;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdir();
                file = new File(fullPath);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.exists();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String result = "";
        String temp;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));
            while ((temp = reader.readLine()) != null) {
                result += temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
