import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import EntryPage from '@/pages/Entry';
import AdminLoginPage from '@/pages/AdminLogin';
import TermsPage from '@/pages/Terms';
import AdminDashBoardPage from '@/pages/AdminDashBoard';
import TempEntryPage from '@/pages/TempEntry';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* 기본 경로 접속 시 엔트리 페이지로 이동 */}
        <Route path='/' element={<TempEntryPage />} />
        {/* <Route path='/' element={<EntryPage />} /> */}
        <Route path='/entry' element={<EntryPage />} />
        <Route path='/admin/login' element={<AdminLoginPage />} />
        <Route path='/admin/dashboard' element={<AdminDashBoardPage />} />
        <Route path='/login/terms' element={<TermsPage />} />

        {/* 없는 페이지 처리 */}
        <Route path='*' element={<Navigate to='/entry' replace />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
