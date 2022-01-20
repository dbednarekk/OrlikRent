import React from 'react';
import Box from '@mui/material/Box'
import Header from '../../components/Header'
import Footer from '../../components/Footer'
import PanelLayout from '../../components/PanelLayout';
import AccountsListIcon from '@mui/icons-material/PeopleAlt';
import ListAccounts from './ListAccounts';

function AdminPage() {
  return <Box>
       <Header title="Admin panel" />
      <Box
        style={{
          height: "100vh",
        }}
      >
          <Box  style={{
        position: "relative",
        top: "20%",
      }}>
              <PanelLayout menu={[
                {
                    link: 'admin/listAccounts',
                    Icon: AccountsListIcon,
                    text: 'list accounts',
                    Component: ListAccounts
                }]}>

                </PanelLayout>
          </Box>
      </Box>
      <Footer />
  </Box>;
}

export default AdminPage;
