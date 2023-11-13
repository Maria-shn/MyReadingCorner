 function addBook() {
    var bookName = document.getElementById("bookName").value;
    var author = document.getElementById("author").value;
    var status = document.getElementById("status").value;

    var bookData = {
        name: bookName,
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
            li.textContent = `${book.name} by ${book.author}`;
            ul.appendChild(li);
        });
        listContainer.appendChild(ul);
    }
}

// Initial update of book lists when the page loads
updateBookLists();