object InventoryManagement {
    case class Product(name: String, quantity: Int, price: Double)

    val inventory1 = Map(
        101 -> Product("Pens", 100, 20.00),
        102 -> Product("Pencils", 80, 15.00),
        103 -> Product("Erasers", 50, 10.00),
        104 -> Product("Sharpners", 45, 20.5),
        105 -> Product ("Typex", 25, 95.6)
    )

    val inventory2 = Map (
        102 -> Product("Pencils", 100, 25.00),
        106 -> Product("Highlighter", 20, 200.00),
        107 -> Product("Book", 45, 215)
    )

    def allProducts(inventory: Map[Int, Product]) : Unit = {
        println("The products in inventory1 are: ")
        inventory1.values.foreach(product => println(product.name))
        println()
    }

    def totalValue(inventory: Map[Int, Product]): Unit = {
        var value = 0.0
        inventory.values.foreach { product =>
            value += product.quantity * product.price
        }
        println(s"Total value of all products in inventory1 ${value}")
        println()
    }

    def checkIfEmpty(inventory1: Map[Int, Product]) : Unit = {
        var  availability = inventory1.isEmpty
        println(s"Is inventory1 empty? ${availability}")
        println()
    } 

    def Merge(inventory1 : Map[Int, Product], inventory2 : Map[Int, Product]) : Unit = {
    val mergedInventory = inventory2.foldLeft(inventory1) {
      case (acc, (id, product2)) =>
        acc.get(id) match {
          case Some(product1) =>
            acc + (id -> Product(product1.name, product1.quantity + product2.quantity, math.max(product1.price, product2.price)))
          case None =>
            acc + (id -> product2)
        }
    }
    println("The updated inventory after merging inventory1 and inventory2: ")
    println(s"${mergedInventory}")
    println()
  }

    def contains(inventory1 : Map[Int, Product]) : Unit = {
        var productID = 0
        print("Enter a product ID: ")
        productID = scala.io.StdIn.readInt()
        if(inventory1.contains(productID)) {
            println(s"Product with ID ${productID} exists")
            println(s"${inventory1(productID)}")
        }
        else {
            println(s"${productID} does not exist in inventory1")
        }
    } 


    def main(args: Array[String]): Unit = {
        allProducts(inventory1) 
        totalValue(inventory1)
        checkIfEmpty(inventory1)
        Merge(inventory1, inventory2)
        contains(inventory1)
        
        
    }
}
