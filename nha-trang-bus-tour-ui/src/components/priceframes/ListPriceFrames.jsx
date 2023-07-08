import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ReactPaginate from 'react-paginate';
import priceFrameService from '../../services/priceframe.service';

const ListPriceFrames = () => {
  const [priceFrameList, setPriceFrameList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [priceFramesPerPage] = useState(5);

  useEffect(() => {
    priceFrameService
      .getAllPriceFrames()
      .then((res) => {
        console.log(res.data);
        setPriceFrameList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredPriceFrames = priceFrameList.filter((priceFrame) => {
    return (
        priceFrame.priceFrameId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
        priceFrame.priceFrameName.toLowerCase().includes(searchTerm.toLowerCase()) 
    );
  });

  const pageCount = Math.ceil(filteredPriceFrames.length / priceFramesPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * priceFramesPerPage;
  const currentPriceFrames = filteredPriceFrames.slice(offset, offset + priceFramesPerPage);

  return (
    <>
      {/* Page Wrapper */}
      <div id="wrapper">
        <SideBar />
        {/* Content Wrapper */}
        <div id="content-wrapper" className="d-flex flex-column">
          {/* Main Content */}
          <div id="content">
            <Header />
            <div className="container-fluid">
              {/* Page Heading */}
              <h1 className="h3 mb-2 text-gray-800">List Price Frames</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Price Frames"
                  value={searchTerm}
                  onChange={handleSearch}
                />
              </div>
              {/* DataTales Example */}
              <div className="card shadow mb-4">
                <div className="card-body">
                  <div className="table-responsive">
                    <table className="table table-bordered" id="" width="100%" cellSpacing="0">
                      <thead>
                        <tr>
                          <th>Price Frame Id</th>
                          <th>Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentPriceFrames.map((priceFrame) => (
                          <tr key={priceFrame.priceFrameId}>
                            <td>{priceFrame.priceFrameId}</td>
                            <td>{priceFrame.priceFrameName}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <ReactPaginate
                previousLabel={'Previous'}
                nextLabel={'Next'}
                breakLabel={'...'}
                breakClassName={'page-item'}
                breakLinkClassName={'page-link'}
                pageCount={pageCount}
                marginPagesDisplayed={2}
                pageRangeDisplayed={5}
                onPageChange={handlePageClick}
                containerClassName={'pagination'}
                activeClassName={'active'}
                previousClassName={'page-item'}
                nextClassName={'page-item'}
                pageClassName={'page-item'}
                previousLinkClassName={'page-link'}
                nextLinkClassName={'page-link'}
                pageLinkClassName={'page-link'}
              />
            </div>
            {/* /.container-fluid */}
          </div>
        </div>
      </div>
    </>
  );
};

export default ListPriceFrames;
