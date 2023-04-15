package com.example.calculator.calcmanager

import javax.inject.Inject
import javax.script.ScriptEngineManager;

class CalcManagerImpl @Inject constructor() : CalcManager {

    override fun eval(input: String): String {
            return try {
                val result = ScriptEngineManager()
                    .getEngineByName("js")
                    .eval(input)
                result.toString()
            } catch (e: Exception) {
                "Error"
            }

    }
}