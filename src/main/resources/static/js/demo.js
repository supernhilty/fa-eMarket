const uploadForm = document.getElementById('formApartment');
const submitButton = document.getElementById('apartmentSubmitButton');
const fileTableBody = document.getElementById('fileTableBodyApartment');

    document.getElementById('formCustomer').addEventListener('submit', function (e) {
        e.preventDefault(); // Ngăn chuyển hướng mặc định của biểu mẫu

        const fileInput = document.getElementById('file2');
        const file = fileInput.files[0];

        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            // Gửi đối tượng FormData đến API bằng Fetch API
            fetch('http://localhost:8080/customer/upload', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    // Kiểm tra xem phản hồi có trạng thái thành công (200 OK) không
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text(); // Chuyển đổi phản hồi thành plain text
                })
                .then(data => {
                    console.log('Phản hồi từ API:', data);

                    // Hiển thị dữ liệu plain text trong thông báo
                    alert(data);

                    // Xử lý phản hồi từ API ở đây nếu cần

                })
                .catch(error => {
                    console.error('Lỗi khi gửi tệp:', error);
                    alert('Lỗi khi tải tệp'); // Thông báo lỗi tải tệp
                    // Xử lý lỗi ở đây nếu cần
                });
        } else {
            console.log('Không có tệp nào được chọn.');
            alert("Không có tệp nào được chọn")
        }
        refreshTableCustomer();
    });

    function refreshTableCustomer() {
        // Lấy đối tượng tbody của bảng
        const tbody = document.getElementById('fileTableBodyCustomer');

        // Gửi yêu cầu GET đến máy chủ để lấy dữ liệu mới
        fetch('http://localhost:8080/customer/customers')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Xóa nội dung cũ của tbody
                tbody.innerHTML = '';

                // Thêm dữ liệu mới vào tbody
                data.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                            <td>${item.id}</td>
                            <td>${item.address}</td>
                            <td>${item.retailPrice}</td>
                            <td>${item.numberOfRoom}</td>
                        `;

                    tbody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Lỗi khi tải lại bảng:', error);
            });
    }

    function refreshTable() {
        // Lấy đối tượng tbody của bảng
        const tbody = document.getElementById('fileTableBodyApartment');

        // Gửi yêu cầu GET đến máy chủ để lấy dữ liệu mới
        fetch('http://localhost:8080/apartment/apartments')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Xóa nội dung cũ của tbody
                tbody.innerHTML = '';

                // Thêm dữ liệu mới vào tbody
                data.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.id}</td>
                        <td>${item.address}</td>
                        <td>${item.retailPrice}</td>
                        <td>${item.numberOfRoom}</td>
                    `;

                    tbody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Lỗi khi tải lại bảng:', error);
            });
    }

    document.getElementById('formApartment').addEventListener('submit', function (e) {
        e.preventDefault(); // Ngăn chuyển hướng mặc định của biểu mẫu

        const fileInput = document.getElementById('file3');
        const file = fileInput.files[0];

        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            // Gửi đối tượng FormData đến API bằng Fetch API
            fetch('http://localhost:8080/apartment/upload', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    // Kiểm tra xem phản hồi có trạng thái thành công (200 OK) không
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text(); // Chuyển đổi phản hồi thành plain text
                })
                .then(data => {
                    console.log('Phản hồi từ API:', data);

                    // Hiển thị dữ liệu plain text trong thông báo
                    alert(data);

                    // Xử lý phản hồi từ API ở đây nếu cần

                })
                .catch(error => {
                    console.error('Lỗi khi gửi tệp:', error);
                    alert('Lỗi khi tải tệp'); // Thông báo lỗi tải tệp
                    // Xử lý lỗi ở đây nếu cần
                });
        } else {
            console.log('Không có tệp nào được chọn.');
            alert("Không có tệp nào được chọn")
        }
        refreshTable();
    });
