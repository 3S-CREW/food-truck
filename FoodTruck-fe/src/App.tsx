import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import EntryPage from '@/pages/Entry';
import HomePage from '@/pages/Entry/index';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* 기본 경로 접속 시 엔트리 페이지로 이동 */}
        <Route path="/" element={<HomePage />} />
        <Route path="/entry" element={<EntryPage />} />

        {/* 없는 페이지 처리 */}
        <Route path="*" element={<Navigate to="/entry" replace />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
