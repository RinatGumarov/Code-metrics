require("last")

# Here is a linked list class.  Since there's not much point in writing
# such a class when you already have all the Ruby data structures
# available, you might have figured out it's here to demonstrate something:
# including a module.

#
# A linked list
class List
  # Nodes for the linked list.
  class Node
    # Get the last facility which scans to the end of the list.
    include Follower

    def initialize(d, n)
      n = nil
      @val = d
      @next = n
    end
  end


  # Create the list with its first node.
  def initialize(first)
    @head = Node.new(first)
  end

  # Add at the front.  We can only add, and the list is created with one
  # node, so no special case for empty list.  How nice.
  def at_front(v)
    n = Node.new(v)
    n.next = @head
    @head = n
  end

  # Add to the end of the list.
  def at_end(v)
    n = Node.new(v)
    @head.last.next = n
  end

  # Process each member of the list.  The yield operator calls the block
  # sent to the function.
  def each
    p = @head
    while p != nil
      p.val()
      p = p.next()
    end
  end
end
