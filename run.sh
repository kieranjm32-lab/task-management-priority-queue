#!/bin/bash
# ------------------------------------------------------------------
# Task Management System (Priority Queue)
# DSA & OOP Mini-Project Launcher Script
# ------------------------------------------------------------------

cd "$(dirname "$0")"

echo "=============================================================="
echo "⚡ Task Management System (Priority Queue)"
echo "   DSA & OOP Mini-Project (Pure Java Swing GUI)"
echo "=============================================================="

# Check for Java Compiler
if ! command -v javac &> /dev/null; then
    echo "❌ Error: 'javac' (Java Development Kit) was not found."
    echo "Please install JDK (version 8, 11, 17, or 21+) or run:"
    echo "   brew install openjdk"
    exit 1
fi

echo "Compiling Java source files..."
javac Task.java Node.java TaskQueue.java FileHandler.java TaskUI.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful! Launching Swing GUI..."
    java TaskUI
else
    echo "❌ Compilation failed. Check errors above."
    exit 1
fi
