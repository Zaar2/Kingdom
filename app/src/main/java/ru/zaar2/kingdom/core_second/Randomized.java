package ru.zaar2.kingdom.core_second;

public class Randomized {
    public Randomized() {
    }
    /**
     * <p>метод используется если необходимо получить число из заданного диапазона</p>
     * @param min минимальное значение диапазона
     * @param max максимальное значение диапазона
     * @return псевдослучайное число
     */
    public double random(int min, int max) {
        return ((Math.random() * ((max - min) + 1)) + min);
    }
    /**
     * <p>метод используется если необходимо получить число из диапазона начинающегося с 1</p>
     * @param max максимальное значение диапазона
     * @return псевдослучайное число
     */
    public double random(int max){
        return (Math.random() * max+1);
    }
    /**
     * <p>метод используется если необходимо получить число из диапазона от 0 до 1</p>
     * @return псевдослучайное число
     */
    public double random(){
        return Math.random();
    }
}
