fun main() {
    println(calculateFrequency(input))
    println(calculateDuplicateFrequency(input))
}

private fun calculateFrequency(input: String): Int {
    var frequency = 0
    
    input.lines().filterNot(String::isEmpty).forEach {
        val instruction = it.first()
        val change = it.substring(1).toInt()
        
        if (instruction == '+') {
            frequency += change
        } else {
            frequency -= change
        }
    }
    
    return frequency
}

private fun calculateDuplicateFrequency(input: String): Int {
    var currentFrequency = 0
    var frequencies: MutableSet<Int> = HashSet()
    
    frequencies.add(currentFrequency)
    
    while(true) {
        input.lines().filterNot(String::isEmpty).forEach {
            val instruction = it.first()
            val change = it.substring(1).toInt()

            if (instruction == '+') {
                currentFrequency += change
            } else {
                currentFrequency -= change
            }

            if (frequencies.contains(currentFrequency)) {
                return currentFrequency
            } else {
                frequencies.add(currentFrequency)
            }
    	}
    }
}
