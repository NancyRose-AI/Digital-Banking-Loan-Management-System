import { useState } from 'react';
import { Outlet, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import {
  AppBar,
  Box,
  CssBaseline,
  Drawer,
  IconButton,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar,
  Typography,
  Button,
  Avatar,
  Divider,
  Tooltip,
  Chip,
} from '@mui/material';
import {
  Menu as MenuIcon,
  Dashboard as DashboardIcon,
  AccountBalance as AccountIcon,
  CreditCard as LoanIcon,
  SwapHoriz as TransferIcon,
  BarChart as ReportsIcon,
  Logout as LogoutIcon,
  AccountBalanceWallet as LogoIcon,
  Shield as ShieldIcon,
} from '@mui/icons-material';

const drawerWidth = 256;

const MainLayout = () => {
  const [mobileOpen, setMobileOpen] = useState(false);
  const { logout, user } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const handleDrawerToggle = () => setMobileOpen(!mobileOpen);

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const menuItems = [
    { text: 'Dashboard',         icon: <DashboardIcon />, path: '/' },
    { text: 'Accounts',          icon: <AccountIcon />,   path: '/accounts' },
    { text: 'Loans & EMI',       icon: <LoanIcon />,      path: '/loans' },
    { text: 'Fund Transfers',    icon: <TransferIcon />,  path: '/transfers' },
    { text: 'Financial Reports', icon: <ReportsIcon />,   path: '/reports' },
  ];

  const drawer = (
    <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column', bgcolor: '#ffffff' }}>
      {/* Brand */}
      <Box sx={{
        px: 3, py: 2.75,
        display: 'flex', alignItems: 'center', gap: 1.5,
        borderBottom: '1px solid #e5e7eb',
      }}>
        <Box sx={{
          width: 36, height: 36, borderRadius: 2,
          bgcolor: '#1d4ed8',
          display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}>
          <LogoIcon sx={{ color: '#ffffff', fontSize: 20 }} />
        </Box>
        <Box>
          <Typography variant="h6" fontWeight="800" color="#111827" sx={{ lineHeight: 1.1, letterSpacing: '-0.3px' }}>
            DigiBank
          </Typography>
          <Typography variant="caption" sx={{ color: '#6b7280', fontSize: '0.7rem', fontWeight: 500 }}>
            Enterprise Banking
          </Typography>
        </Box>
      </Box>

      {/* Navigation */}
      <Box sx={{ px: 2, pt: 2.5, pb: 1, flexGrow: 1 }}>
        <Typography variant="caption" sx={{
          px: 1.5, mb: 1.5, display: 'block',
          color: '#9ca3af', fontWeight: 700, letterSpacing: '0.06em', fontSize: '0.68rem',
          textTransform: 'uppercase',
        }}>
          Navigation
        </Typography>
        <List disablePadding sx={{ display: 'flex', flexDirection: 'column', gap: 0.5 }}>
          {menuItems.map((item) => {
            const isActive = location.pathname === item.path;
            return (
              <ListItem key={item.text} disablePadding>
                <ListItemButton
                  onClick={() => {
                    navigate(item.path);
                    setMobileOpen(false);
                  }}
                  sx={{
                    borderRadius: 2,
                    px: 1.75,
                    py: 1.1,
                    bgcolor: isActive ? '#eff6ff' : 'transparent',
                    color: isActive ? '#1d4ed8' : '#374151',
                    '&:hover': {
                      bgcolor: isActive ? '#eff6ff' : '#f9fafb',
                      color: isActive ? '#1d4ed8' : '#111827',
                    },
                    '& .MuiListItemIcon-root': {
                      color: isActive ? '#1d4ed8' : '#6b7280',
                      minWidth: 38,
                    },
                    '&:hover .MuiListItemIcon-root': {
                      color: isActive ? '#1d4ed8' : '#374151',
                    },
                  }}
                >
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  <ListItemText
                    primary={item.text}
                    primaryTypographyProps={{
                      fontSize: '0.88rem',
                      fontWeight: isActive ? 700 : 500,
                      color: 'inherit',
                    }}
                  />
                  {isActive && (
                    <Box sx={{
                      width: 4, height: 24, borderRadius: 2,
                      bgcolor: '#1d4ed8', ml: 1, flexShrink: 0,
                    }} />
                  )}
                </ListItemButton>
              </ListItem>
            );
          })}
        </List>
      </Box>

      {/* Security badge */}
      <Box sx={{ px: 2, pb: 2 }}>
        <Box sx={{
          px: 2, py: 1.5,
          bgcolor: '#f0fdf4',
          border: '1px solid #bbf7d0',
          borderRadius: 2,
          display: 'flex', alignItems: 'center', gap: 1.5,
        }}>
          <ShieldIcon sx={{ color: '#16a34a', fontSize: 18, flexShrink: 0 }} />
          <Box>
            <Typography variant="caption" sx={{ color: '#15803d', fontWeight: 700, display: 'block', fontSize: '0.72rem' }}>
              Secure Session
            </Typography>
            <Typography variant="caption" sx={{ color: '#4ade80', fontSize: '0.68rem' }}>
              256-bit encrypted
            </Typography>
          </Box>
        </Box>
      </Box>

      {/* Footer */}
      <Box sx={{
        px: 3, py: 1.5,
        borderTop: '1px solid #e5e7eb',
        textAlign: 'center',
      }}>
        <Typography variant="caption" sx={{ color: '#9ca3af', fontSize: '0.7rem', fontWeight: 500 }}>
          DigiBank Corp. v1.4.2
        </Typography>
      </Box>
    </Box>
  );

  const userInitial = user?.username ? user.username.charAt(0).toUpperCase() : 'U';

  return (
    <Box sx={{ display: 'flex', minHeight: '100vh', bgcolor: '#f3f4f6' }}>
      <CssBaseline />

      {/* Top AppBar */}
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
          bgcolor: '#ffffff',
          color: '#111827',
          boxShadow: 'none',
          borderBottom: '1px solid #e5e7eb',
        }}
      >
        <Toolbar sx={{ minHeight: 64, px: { xs: 2, sm: 3 } }}>
          <IconButton
            color="inherit"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: 'none' }, color: '#374151' }}
          >
            <MenuIcon />
          </IconButton>

          {/* Page breadcrumb */}
          <Box sx={{ display: { xs: 'none', sm: 'block' } }}>
            <Typography variant="body2" color="text.secondary" fontWeight="500" sx={{ fontSize: '0.83rem' }}>
              {menuItems.find(m => m.path === location.pathname)?.text || 'Dashboard'}
            </Typography>
          </Box>

          <Box sx={{ flexGrow: 1 }} />

          {/* User info + logout */}
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
            <Box sx={{ display: { xs: 'none', sm: 'flex' }, alignItems: 'center', gap: 1.25 }}>
              <Avatar
                sx={{
                  width: 34, height: 34,
                  fontSize: '0.875rem', fontWeight: 700,
                  bgcolor: '#1d4ed8', color: '#fff',
                }}
              >
                {userInitial}
              </Avatar>
              <Box>
                <Typography variant="body2" fontWeight="700" color="text.primary" sx={{ lineHeight: 1.2, fontSize: '0.85rem' }}>
                  {user?.username}
                </Typography>
                <Typography variant="caption" color="text.secondary" fontWeight="500" sx={{ fontSize: '0.73rem' }}>
                  {user?.roles?.[0]?.replace('ROLE_', '') || 'Customer'}
                </Typography>
              </Box>
            </Box>

            <Divider orientation="vertical" flexItem sx={{ height: 24, my: 'auto', display: { xs: 'none', sm: 'block' } }} />

            <Tooltip title="Sign Out">
              <Button
                variant="outlined"
                size="small"
                onClick={handleLogout}
                startIcon={<LogoutIcon sx={{ fontSize: '16px !important' }} />}
                sx={{
                  borderColor: '#e5e7eb',
                  color: '#6b7280',
                  fontSize: '0.8rem',
                  py: 0.6,
                  px: 1.5,
                  borderRadius: 2,
                  fontWeight: 600,
                  '&:hover': {
                    borderColor: '#fecaca',
                    color: '#dc2626',
                    bgcolor: '#fef2f2',
                    transform: 'none',
                  },
                }}
              >
                Logout
              </Button>
            </Tooltip>
          </Box>
        </Toolbar>
      </AppBar>

      {/* Drawer */}
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
      >
        <Drawer
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{ keepMounted: true }}
          sx={{
            display: { xs: 'block', sm: 'none' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: 'none', sm: 'block' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>

      {/* Main Content */}
      <Box
        component="main"
        className="animate-fade-in"
        sx={{
          flexGrow: 1,
          p: { xs: 2.5, sm: 3.5 },
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          minHeight: '100vh',
          mt: '64px',
        }}
      >
        <Outlet />
      </Box>
    </Box>
  );
};

export default MainLayout;
