/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import communication.Communication;
import coordinator.Coordinator;

/**
 *
 * @author neven
 */
public class Main {
    public static void main(String[] args) {
        Coordinator.getInstance().openLoginForm();
    }
}
