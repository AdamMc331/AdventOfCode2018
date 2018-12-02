fun main() {
    println(calculateChecksum(input))
    println(findBestMatch(input))
}

data class Pairing(val firstInput: String, val secondInput: String) {
    
    init {
        assert(firstInput.length == secondInput.length)
    }
    
    val differences: Int
    	get() = firstInput.zip(secondInput).count { it.first != it.second }
        
   	val commonCharacters: String
    	get() = firstInput.zip(secondInput).filter { it.first == it.second }.map { it.first }.joinToString(separator = "")
}

private fun findBestMatch(input: String): String {
    val inputs = input.lines()
    
    val pairings: MutableList<Pairing> = ArrayList()
    
    for (firstIndex in (0..inputs.size - 2)) {
        for (secondIndex in (firstIndex + 1..inputs.size - 1)) {
            val firstInput = inputs[firstIndex]
            val secondInput = inputs[secondIndex]
            
            pairings.add(Pairing(firstInput, secondInput))
        }
    }
    
    return pairings.minBy { it.differences }?.commonCharacters.orEmpty()
}

private fun calculateChecksum(input: String): Int {
    var inputsWithALetterTwice = 0
    var inputsWithALetterThreeTimes = 0
    
    input.lines().forEach {
        val letterAppearances: MutableMap<Char, Int> = HashMap()
        
        it.forEach {
            val currentCount = letterAppearances.getOrDefault(it, 0)
            letterAppearances[it] = currentCount + 1
        }
        
        val hasTwo = letterAppearances.any { it.value == 2 }
        val hasThree = letterAppearances.any { it.value == 3 }
		
        if (hasTwo) inputsWithALetterTwice++
        if (hasThree) inputsWithALetterThreeTimes++    
    }
    
    return inputsWithALetterTwice * inputsWithALetterThreeTimes
}
