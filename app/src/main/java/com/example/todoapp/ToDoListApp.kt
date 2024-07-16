package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityToDoListAppBinding

class ToDoListApp : AppCompatActivity() {
    private lateinit var binding: ActivityToDoListAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val todoList = mutableListOf(
            ToDo("Follow Us on X", false),
            ToDo("Prank My Boss", false),
            ToDo("Do the assignment", true),
            ToDo("Clean The Dishes", false),
            ToDo("Follow up the Project", true),
            ToDo("Ask For Help", false),
            ToDo("Take BreakFast", true)
        )

        val adapter = ToDoAdapter(todoList)
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val title = binding.etTodo.text.toString()
            if (title.isNotEmpty()) {
                val todo = ToDo(title, false)
                todoList.add(todo)
                adapter.notifyItemInserted(todoList.size - 1)
                binding.etTodo.text.clear()
            }
        }
    }
}
