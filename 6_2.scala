object Student {
    def getStudentInfo(): (String, Int, Int, Double, Char) = {
        print("Enter Student's name: ")
        val name = scala.io.StdIn.readLine()
        
        print("Enter the mark: ")
        val mark = scala.io.StdIn.readInt()
        
        print("Enter the total possible marks: ")
        val totalMark = scala.io.StdIn.readInt()
       
        val percentage = (mark.toDouble / totalMark) * 100
        
        val grade = percentage match {
            case p if p >= 90 => 'A'
            case p if p >= 75 => 'B'
            case p if p >= 50 => 'C'
            case _ => 'D'
        }

        (name, mark, totalMark, percentage, grade)
    }

    def printStudentRecord(r: (String, Int, Int, Double, Char)): Unit = {
        println(s"Name: ${r._1}")
        println(s"Mark: ${r._2}")
        println(s"Total Marks: ${r._3}")
        println(f"Percentage: ${r._4}%.2f")
        println(s"Grade: ${r._5}")
    }

    def validateInput(name: String, mark: Int, totalMark: Int): (Boolean, Option[String]) = {
        if (name.isEmpty || totalMark <= 0 || mark < 0 || mark < totalMark) {
            (false, Some("The details entered are invalid"))
        } else {
            (true, None)
        }
    }

    def getStudentInfoWithRetry(): (String, Int, Int, Double, Char) = {
        var isValid = false
        var result: (String, Int, Int, Double, Char) = null

        while (!isValid) {
            val (name, mark, totalMark, percentage, grade) = getStudentInfo()
            val (valid, errorMessage) = validateInput(name, mark, totalMark)
            
             if (valid) {
                isValid = true
                result = (name, mark, totalMark, percentage, grade)
            } else {
                println(errorMessage.get)
            }
        }

        result
    }

    def option(opt: Int, studentRecord: Option[(String, Int, Int, Double, Char)]): Option[(String, Int, Int, Double, Char)] = {
        opt match {
            case 1 => Some(getStudentInfoWithRetry())
            case 2 => 
                studentRecord match {
                    case Some(record) => printStudentRecord(record)
                    case None => println("No student record available.")
                }
                studentRecord
            case 3 => 
                println("Exiting...")
                studentRecord
            case _ => 
                println("Invalid option")
                studentRecord
        }
    }
    
    def main(args: Array[String]): Unit = {
        var opt = 0
        var studentRecord: Option[(String, Int, Int, Double, Char)] = None

        while(opt != 3) {
            println("Student management system")
            println("1: Enter Student Details")
            println("2: View Student Details")
            println("3: Exit")
            print("Enter the option number: ")
            opt = scala.io.StdIn.readInt()
            studentRecord = option(opt, studentRecord)
        }   
    }
}
