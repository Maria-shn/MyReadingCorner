 function addBook() {
    var bookName = document.getElementById("bookName").value;
    var author = document.getElementById("author").value;
    var status = document.getElementById("status").value;

    var bookData = {
        title: bookName,
        author: author,
        status: status
    };

    fetch('/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(bookData),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Book added:', data);
        // Update the book lists
        updateBookLists();
    })
    .catch(error => console.error('Error:', error));

    // Clear form inputs after adding the book
    document.getElementById("bookName").value = "";
    document.getElementById("author").value = "";
}


function updateBookLists() {
    fetch('/books')
    .then(response => response.json())
    .then(books => {
       
        var wantToReadList = books.filter(book => book.status === 'Want To Read');
        var currentlyReadingList = books.filter(book => book.status === 'Currently Reading');
        var finishedList = books.filter(book => book.status === 'Finished');

        // Update the HTML of the lists
        updateList('wantToReadList', wantToReadList);
        updateList('currentlyReadingList', currentlyReadingList);
        updateList('finishedList', finishedList);
    })
    .catch(error => console.error('Error:', error));
}

// Function to update the HTML of a book list
function updateList(listId, books) {
    var listContainer = document.getElementById(listId);
    listContainer.innerHTML = ''; // Clear the previous content

    if (books.length === 0) {
        listContainer.innerHTML = '<p>No books in this category</p>';
    } else {
        var ul = document.createElement('ul');
        books.forEach(book => {
            var li = document.createElement('li');
            li.textContent = `${book.id} ${book.title} by ${book.author}`;
            ul.appendChild(li);
        });
        listContainer.appendChild(ul);
    }
}

async function updateBookStatus() {
    var bookId = document.getElementById("updateId").value;
    var newStatus = document.getElementById("updateStatus").value;

    try {
        // Make a PUT request to update the book status
        const response = await fetch(`/books/${bookId}?status=${newStatus}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            alert('Status updated successfully!');
            updateBookLists(); // Refresh book information after update
        } else {
            const errorData = await response.json();
            alert('Error updating status: ' + errorData.message);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
async function deleteBookById() {
    var deleteId = document.getElementById("deleteId").value;
    try {
        // Make a DELETE request to update the book status
        const response = await fetch(`/books?id=${deleteId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        updateBookLists();
    } catch (error) {
        console.error('Error:', error);
        alert('Error deleting book. Please try again.');
    }
}



async function deleteBookByTitleAndAuthor() {
    var deleteTitle = document.getElementById("deleteTitle").value;
    var deleteAuthor = document.getElementById("deleteAuthor").value;
    try {
        // Make a DELETE request to update the book status
        const response = await fetch(`/books?id=0&title=${deleteTitle}&author=${deleteAuthor}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const deletedBook = await response.json();
            alert(`Book "${deletedBook.title}" by ${deletedBook.author} deleted successfully!`);
            updateBookLists(); // Refresh book information after update
        } else {
            const errorData = await response.json();
            alert('Error deleting book: ' + errorData.message);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error deleting book. Please try again.');
    }
}

// Initial update of book lists when the page loads
updateBookLists();